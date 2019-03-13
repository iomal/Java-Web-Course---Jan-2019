package exam.web.beans;

import exam.domain.models.view.DocumentDetailViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named("details")
@ViewScoped
public class DocumentDetailsBean extends BaseBean implements Serializable {
    private DocumentDetailViewModel model;
    private ModelMapper modelMapper = new ModelMapper();
    private DocumentService documentService;
    private String id;

    public DocumentDetailsBean() {
    }

    @Inject
    public DocumentDetailsBean(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostConstruct
    public void init() {
        id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");

        this.model = this.modelMapper.map(this.documentService.getDocumentById(id), DocumentDetailViewModel.class);
    }

    public DocumentDetailViewModel getModel() {
        return this.model;
    }

    public void setModel(DocumentDetailViewModel model) {
        this.model = model;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
