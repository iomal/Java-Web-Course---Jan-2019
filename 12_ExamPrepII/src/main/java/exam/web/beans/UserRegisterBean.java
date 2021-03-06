package exam.web.beans;

import exam.domain.entities.Gender;
import exam.domain.models.binding.UserRegisterBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

@Named("register")
@RequestScoped
public class UserRegisterBean extends BaseBean {
    private UserRegisterBindingModel model;
    private ModelMapper modelMapper;
    private UserService userSerivce;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userSerivce = userService;
        this.init();
    }

    @PostConstruct
    public void init() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return this.model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }
    public Gender[] getGenders() {
        return Gender.values();
    }
    public void register() throws SystemException {
        if (!this.userSerivce.save(this.modelMapper.map(model, UserServiceModel.class)) ||
                !model.getPassword().equals(model.getConfirmPassword())) {
            this.redirect("/register");
        } else this.redirect("/login");
    }
}
