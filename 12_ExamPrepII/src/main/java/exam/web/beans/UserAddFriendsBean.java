package exam.web.beans;

import exam.domain.models.service.UserServiceModel;
import exam.domain.models.service.UserServiceNotFriendsModel;
import exam.domain.models.view.UserFriendViewModel;
import exam.domain.models.view.UserPossibleFriendViewModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named("addFriends")
@RequestScoped
public class UserAddFriendsBean extends BaseBean {
    private List<UserPossibleFriendViewModel> friendsToAdd;
    private String id;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserAddFriendsBean() {
    }

    @Inject
    public UserAddFriendsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() throws SystemException {
        id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        List<UserServiceNotFriendsModel> users = this.userService.findAllPossibleFriends(id);
        friendsToAdd = users.stream()
                .map(f -> modelMapper.map(f, UserPossibleFriendViewModel.class)).collect(Collectors.toList());
        System.out.println();
    }

    public void makeFriend(String idFriend) throws SystemException {
        this.userService.addFriend(id, idFriend);
        super.redirect("/home");
    }

    public List<UserPossibleFriendViewModel> getFriendsToAdd() {
        return this.friendsToAdd;
    }

    public void setFriendsToAdd(List<UserPossibleFriendViewModel> friendsToAdd) {
        this.friendsToAdd = friendsToAdd;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
