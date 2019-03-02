package exam.repository;

import exam.domain.entities.Job;

import java.io.Serializable;

public interface JobRepository extends GenericRepository<Job,String> {
    void delete(Job job);
}
