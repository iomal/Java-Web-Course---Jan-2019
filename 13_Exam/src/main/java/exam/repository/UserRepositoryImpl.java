package exam.repository;

import exam.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl extends BaseRepository implements UserRespository {

    @Inject
    protected UserRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public User save(User user) {
        return this.executeTransaction((em) -> {
            em.persist(user);
            return user;
        });
    }

    @Override
    public User findById(String id) {
        return this.executeTransaction((em) ->
                em.find(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return this.executeTransaction(em ->
                em.createQuery("SELECT u FROM User as u", User.class).getResultList());
    }

    @Override
    public User findByUserName(String username) {
        return this.executeTransaction(em ->
                em.createQuery("SELECT u FROM User as u where u.username = :username", User.class)
                        .setParameter("username", username)
                        .getSingleResult());
    }
}
