package exam.web.beans;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.domain.models.view.UserFriendViewModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("friends")
@RequestScoped
public class UserFriendsListBean extends BaseBean {
    private List<UserFriendViewModel> friends;

    private UserService userService;
    private ModelMapper modelMapper;
    private String id;

    public UserFriendsListBean() {
    }

    @Inject
    public UserFriendsListBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() throws SystemException {
        id =(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        UserServiceModel user = this.userService.finById(id);
        friends = Arrays.asList(this.modelMapper.map(user.getFriends(), UserFriendViewModel[].class));
        
    }

    public List<UserFriendViewModel> getFriends() {
        return this.friends;
    }

    public void unfriend(String idUnfriend) throws SystemException {
        this.userService.unfriend(id, idUnfriend);
        super.redirect("/friends");

    }

      public void setFriends(List<UserFriendViewModel> friends) {
        this.friends = friends;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
