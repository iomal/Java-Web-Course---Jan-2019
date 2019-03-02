package exam.service;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;
import exam.domain.models.service.UserServiceNotFriendsModel;

import javax.transaction.SystemException;
import java.util.List;

public interface UserService {
    UserServiceModel finById(String id);
    List<UserServiceModel> findAll();
    List<UserServiceNotFriendsModel> findAllPossibleFriends(String id);
    UserServiceModel findByName(String username);
    boolean save(UserServiceModel userServiceModel);
    void addFriend (String id, String friendId);
    void unfriend(String id,String unfriendId);

}
