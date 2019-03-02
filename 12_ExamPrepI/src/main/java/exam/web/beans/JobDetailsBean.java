package exam.web.beans;

import exam.domain.models.binding.JobBindingModel;
import exam.domain.models.service.JobServiceModel;
import exam.service.JobService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("details")
@RequestScoped
public class JobDetailsBean {
    private JobService jobService;
    private ModelMapper modelMapper;
    private JobBindingModel jobBindingModel;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    public JobBindingModel getJobBindingModel() {
        return this.jobBindingModel;
    }

    public void setJobBindingModel(JobBindingModel jobBindingModel) {
        this.jobBindingModel = jobBindingModel;
    }

    public void jobDetails(String id){
        JobServiceModel jobServiceModel = this.jobService.finById(id);
        jobBindingModel = modelMapper.map(jobServiceModel,JobBindingModel.class);
        jobBindingModel.setSector(jobServiceModel.getSector().toString());
    }
}
