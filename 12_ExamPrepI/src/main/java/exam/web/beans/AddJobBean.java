package exam.web.beans;

import exam.domain.entities.Sector;
import exam.domain.models.binding.AddJobBindingModel;
import exam.domain.models.binding.JobBindingModel;
import exam.domain.models.service.JobServiceModel;
import exam.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

@Named("add")
@RequestScoped
public class AddJobBean extends BaseBean {
    private AddJobBindingModel addJobBindingModel;
    private JobService jobService;
    private ModelMapper modelMapper;

    public AddJobBean() {
    }

    public AddJobBindingModel getAddJobBindingModel() {
        return this.addJobBindingModel;
    }

    @Inject
    public AddJobBean(JobService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        addJobBindingModel = new AddJobBindingModel();}

    public Sector[] getSectors() {
        return Sector.values();
    }

    public void addJob() {
        JobServiceModel job = modelMapper.map(addJobBindingModel, JobServiceModel.class);
        //job.setSector(Sector.valueOf(addJobBindingModel.getSector().toLowerCase()));
        if (this.jobService.save(job))
            this.redirect("/home");
    }
}
