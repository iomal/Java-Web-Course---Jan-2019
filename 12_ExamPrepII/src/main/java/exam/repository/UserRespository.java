package exam.repository;

import exam.domain.entities.User;

import javax.transaction.SystemException;
import java.util.List;

public interface UserRespository extends GenericRepository<User, String> {
    User findByUserName(String userName);
    User addFriend(String id, String friendId);
    void unfriend(String id, String unfriendId);
    List<User> notFriends(String id);
}
