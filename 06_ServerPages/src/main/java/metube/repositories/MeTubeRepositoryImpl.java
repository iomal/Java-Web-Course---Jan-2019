package metube.repositories;

import metube.domain.entities.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import java.util.*;

public class MeTubeRepositoryImpl implements MeTubeRepository {
    private final EntityManager entityManager;

    @Inject
    public MeTubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Tube> findByUploader(String uploaderName) {
//        this.entityManager.getTransaction().begin();
//        List<Tube> tubes = this.entityManager
//                .createQuery("SELECT t FROM Tube t WHERE t.uploader = :uploader", Tube.class)
//                .setParameter("uploader", uploaderName)
//                .getResultList();
//        this.entityManager.getTransaction().commit();
//        return tubes;

        this.entityManager.getTransaction().begin();
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tube> query = cb.createQuery(Tube.class);
        Root<Tube> t = query.from(Tube.class);
        ParameterExpression<String> p = cb.parameter(String.class);
        query.select(t).where(cb.equal(t.get("uploader"), uploaderName));
        this.entityManager.clear();
        TypedQuery<Tube> q = entityManager.createQuery(query);
        List<Tube> results = q.getResultList();
        this.entityManager.getTransaction().commit();
        return results;
    }

    @Override
    public void save(Tube entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<Tube> findAll() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Tube> query = cb.createQuery(Tube.class);
        Root<Tube> t = query.from(Tube.class);
        TypedQuery<Tube> q = entityManager.createQuery(query);
        List<Tube> results = new ArrayList<>();
        try {
            results = q.getResultList();
            return results;
        } catch (Exception e) {
            return results;
        }
    }

    @Override
    public Optional<Tube> findById(String id) {
        this.entityManager.getTransaction().begin();
        Optional<Tube> tube = Optional.ofNullable(this.entityManager.find(Tube.class, id));
        this.entityManager.getTransaction().commit();
        return tube;
    }

}
