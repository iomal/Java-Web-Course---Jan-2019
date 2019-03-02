package exam.web.beans;

import exam.domain.models.view.UserFriendViewModel;
import exam.domain.models.view.UserPossibleFriendViewModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

@Named("profile")
@RequestScoped
public class FriendDetailsBean {
    private UserPossibleFriendViewModel user;
    private UserService userService;
    private ModelMapper modelMapper;

    public FriendDetailsBean() {
    }

    @Inject
    public FriendDetailsBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        user = modelMapper.map(this.userService.finById(id), UserPossibleFriendViewModel.class);
    }

    public UserPossibleFriendViewModel getUser() {
        return this.user;
    }
}
