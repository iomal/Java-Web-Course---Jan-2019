package exam.repository;

import exam.domain.entities.Job;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public class JobRepositoryImpl extends BaseRepository implements JobRepository {
    @Inject
    protected JobRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Job save(Job job) {
        return executeTransaction(em -> {
            em.persist(job);
            return job;
        });
    }

    @Override
    public Job findById(String id) {
        return executeTransaction(em -> em.find(Job.class, id));
    }

    @Override
    public List<Job> findAll() {
        return executeTransaction(em -> em.createQuery("SELECT j FROM Job as j", Job.class)
                .getResultList());
    }

    @Override
    public void delete(Job job) {
        executeTransaction(em->
        {em.createNativeQuery("DELETE FROM job WHERE  id='" + job.getId() + "'").executeUpdate();
        return null;});
    }
}
