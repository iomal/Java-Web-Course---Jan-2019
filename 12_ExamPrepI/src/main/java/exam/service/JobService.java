package exam.service;

import exam.domain.models.service.JobServiceModel;
import exam.domain.models.service.UserServiceModel;

import java.io.Serializable;
import java.util.List;

public interface JobService {
    JobServiceModel finById(String id);
    List<JobServiceModel> findAll();
    boolean save(JobServiceModel jobServiceModel);
    void delete(JobServiceModel jobServiceModel);
}
