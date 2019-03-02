package exam.service;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel finById(String id);
    List<UserServiceModel> findAll();
    UserServiceModel findByName(String username);
    boolean save(UserServiceModel userServiceModel);
}
