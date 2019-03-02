package exam.web.beans;

import exam.domain.models.service.JobServiceModel;
import exam.service.JobService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("list")
@RequestScoped
public class JobListBean implements Serializable {
    private JobService jobService;
    private JobServiceModel[][] jobs;
    private List<JobServiceModel> jobsServModel;

    public JobListBean() {
    }

    @Inject
    public JobListBean(JobService jobService) {
        this.jobService = jobService;
    }

    @PostConstruct
    public void init() {
        jobs = new JobServiceModel[3][3];
        jobsServModel = this.jobService.findAll();
    }
//Jagged Array to give to JSF for visualization of 3 items per row
    public JobServiceModel[][] JobsN(Integer cols) {
        List<JobServiceModel> jobServiceModels = this.jobService.findAll();
        int index = 0;
        int rows = (int) Math.ceil(jobServiceModels.size() / Double.valueOf(cols));
        jobs = new JobServiceModel[rows][];
        for (int i = 0; i < rows; i++) {
            if (i != rows - 1) {
                jobs[i] = new JobServiceModel[cols];
            } else jobs[i] = new JobServiceModel[jobServiceModels.size() - index];
            for (int j = 0; j < cols; j++) {
                if (index < jobServiceModels.size()) {

                    jobs[i][j] = jobServiceModels.get(index);
                    index++;
                } else break;
            }
        }
        return this.jobs;
    }

    public JobServiceModel[][] getJobs() {
        return this.jobs;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    public List<JobServiceModel> getJobsServModel() {
        return this.jobsServModel;
    }

    public void setJobsServModel(List<JobServiceModel> jobsServModel) {
        this.jobsServModel = jobsServModel;
    }
}
