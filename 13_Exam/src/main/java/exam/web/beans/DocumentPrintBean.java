package exam.web.beans;

import exam.domain.models.binding.ScheduleCreateBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentDetailViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

@Named("print")
@ViewScoped
public class DocumentPrintBean extends BaseBean implements Serializable {
    private DocumentDetailViewModel model;
    private ModelMapper modelMapper = new ModelMapper();
    private DocumentService documentService;
    private String id;

    public DocumentPrintBean() {
    }

    @Inject
    public DocumentPrintBean(DocumentService documentService) {
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

    public void print() {
        this.documentService.deleteDocument(id);

        super.redirect("/home");
    }

    public void sendPdf() throws IOException {


        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response =
                (HttpServletResponse) context.getExternalContext().getResponse();
        response.reset();
        response.setContentType("application/pdf");
        response.setHeader("Content-disposition",
                "inline=filename=file.pdf");

        try {
            byte[] file = this.documentService.getDocumentById(id).getContent().getBytes();
            response.getOutputStream().write(file,0,file.length);
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
