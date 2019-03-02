package exam.service;

import exam.domain.entities.Job;
import exam.domain.models.service.JobServiceModel;
import exam.domain.models.service.UserServiceModel;
import exam.repository.JobRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.mail.internet.MimePartDataSource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class JobServiceImpl implements JobService,Serializable {
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public JobServiceModel finById(String id) {
        return modelMapper.map(this.jobRepository.findById(id), JobServiceModel.class);
    }

    @Override
    public List<JobServiceModel> findAll() {
        return Arrays.asList(modelMapper.map(this.jobRepository.findAll(), JobServiceModel[].class));
    }

    @Override
    public boolean save(JobServiceModel jobServiceModel) {
        Job job = modelMapper.map(jobServiceModel, Job.class);
        return this.jobRepository.save(job) != null;
    }

    @Override
    public void delete(JobServiceModel jobServiceModel) {
        this.jobRepository.delete(modelMapper.map(jobServiceModel,Job.class));
    }
}
