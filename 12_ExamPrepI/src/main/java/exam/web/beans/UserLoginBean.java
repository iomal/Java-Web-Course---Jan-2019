package exam.web.beans;

import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Named("login")
@RequestScoped
public class UserLoginBean extends BaseBean {
    private UserLoginBindingModel model;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return this.model;
    }

    public void login() {
        UserServiceModel userServiceModel = modelMapper.map(this.userService.findByName(model.getUsername()), UserServiceModel.class);
        if (userServiceModel != null && userServiceModel.getPassword().equals(DigestUtils.sha256Hex(model.getPassword()))) {
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
                 sessionMap.put("userId",userServiceModel.getId());
                 sessionMap.put("username",userServiceModel.getUsername());
            this.redirect("/home");
        }
    }
}
