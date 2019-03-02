package exam.web.beans;

import exam.domain.models.binding.ScheduleCreateBindingModel;
import exam.domain.models.binding.UserRegisterBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.DocumentService;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("schedule")
@RequestScoped
public class ScheduleCreateBean  {
    private ScheduleCreateBindingModel model;
    private ModelMapper modelMapper;
    private DocumentService documentService;

    public ScheduleCreateBean() {
    }

    @Inject
    public ScheduleCreateBean(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init() {
        this.model = new ScheduleCreateBindingModel();
    }

    public ScheduleCreateBindingModel getModel() {
        return this.model;
    }

    public void setModel(ScheduleCreateBindingModel model) {
        this.model = model;
    }

    public void schedule() throws IOException {
        DocumentServiceModel documentServiceModel = this.documentService
                .scheduleDocument(this.modelMapper.map(model, DocumentServiceModel.class));
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/views/details.xhtml?id="+documentServiceModel.getId());
    }
}
