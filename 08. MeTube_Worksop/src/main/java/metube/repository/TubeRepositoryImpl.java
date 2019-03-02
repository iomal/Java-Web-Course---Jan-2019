package metube.repository;

import metube.domain.entities.Tube;
import metube.domain.entities.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;
    private final UserRepository userRepository;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    @Override
    public Tube save(Tube entity) {
        this.entityManager.persist(entity);
        User user = this.userRepository.findByUsername(entity.getUploader().getUsername());
        user.addTube(entity);
        return entity;
    }

    @Override
    public List<Tube> findAll() {
        List<Tube> allTubes = this.entityManager
                .createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();
        return allTubes;
    }

    @Override
    public Tube findById(String id) {
        Tube tube = this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();
        return tube;
    }

    @Override
    public long size() {
        long size = this.entityManager
                .createQuery("SELECT count(t) FROM Tube t", long.class)
                .getSingleResult();
        return size;
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.merge(tube);
        return tube;
    }
}
