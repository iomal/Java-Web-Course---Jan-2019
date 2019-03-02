package exam.repository;

import exam.domain.entities.User;
import exam.domain.models.service.UserServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.SystemException;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRespository {

    @Inject
    protected UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User save(User user){
        return this.executeTransaction((em) -> {
            em.persist(user);
            return user;
        });
    }

    @Override
    public User findById(String id){
        return this.executeTransaction((em) -> em.find(User.class,id));
    }

    @Override
    public List<User> findAll(){
        return this.executeTransaction(em ->
                em.createQuery("SELECT u FROM User as u",User.class).getResultList());
    }

    @Override
    public User findByUserName(String username){
        return this.executeTransaction(em->
                em.createQuery("SELECT u FROM User as u where u.username = :username",User.class)
                        .setParameter("username",username)
                        .getSingleResult());
    }

    @Override
    public User addFriend(String id, String friendId){
        User userToAddFriend = this.findById(id);
        User friendToAdd = this.findById(friendId);
        userToAddFriend.getFriends().add(friendToAdd);
        return this.executeTransaction(em->
                em.merge(userToAddFriend));
    }

    @Override
    public void unfriend(String id, String unfriendId){
        User user = this.findById(id);
        User friend = this.findById(unfriendId);
        List<User> friends = user.getFriends();
        friends.remove(friend);
        this.executeTransaction(em-> em.merge(user));
    }

    @Override
    public List<User> notFriends(String id){
        return this.executeTransaction(em->
                em.createNativeQuery("select u2.id,u2.username,u2.gender,u2.password from users as u2 left join" +
                        "(SELECT u.id FROM USERS u WHERE EXISTS(SELECT NULL FROM friends f WHERE u.id = f.id " +
                        " AND f.friends_id = ?) OR EXISTS(SELECT NULL FROM friends f  " +
                        "WHERE u.id = f.friends_id AND f.id = ?)) as a on u2.id= a.id where a.id is null and u2.id<>?;"
                        ,User.class)
                        .setParameter(1,id).setParameter(2,id).setParameter(3,id)
                        .getResultList());
    }
}
