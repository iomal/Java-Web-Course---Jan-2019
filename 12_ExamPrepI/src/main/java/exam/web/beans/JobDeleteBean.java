package exam.web.beans;

import exam.domain.models.binding.JobBindingModel;
import exam.domain.models.service.JobServiceModel;
import exam.service.JobService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import java.io.Serializable;

@Named("delete")
@RequestScoped
public class JobDeleteBean extends BaseBean implements Serializable {
    private JobService jobService;
    private ModelMapper modelMapper = new ModelMapper();
    private JobBindingModel jobBindingModel;
    @ManagedProperty("#{param.id}")
    private String id;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobService jobService) {
        this.jobService = jobService;
    }

    @PostConstruct
    public void init() {
     id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (id != null)
            jobBindingModel = modelMapper.map(this.jobService.finById(id), JobBindingModel.class);
        else jobBindingModel = new JobBindingModel();

    }

    public JobBindingModel getJobBindingModel() {
        return this.jobBindingModel;
    }


    public void delete() {

        jobBindingModel = modelMapper.map(this.jobService.finById(id), JobBindingModel.class);
        this.jobService.delete(modelMapper.map(jobBindingModel, JobServiceModel.class));
        this.redirect("/home");
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
