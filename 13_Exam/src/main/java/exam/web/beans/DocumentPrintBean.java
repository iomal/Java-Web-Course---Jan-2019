package exam.web.beans;

import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import exam.domain.models.view.DocumentDetailViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;


import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

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

        try {
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response =
                    (HttpServletResponse) context.getExternalContext().getResponse();
            String fileName = "dynamic.pdf";
            Document document = new Document();
            OutputStream output = response.getOutputStream();
            PdfWriter writer = PdfWriter.getInstance(document, output);
            document.open();
            addContent(document);
            document.close();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private void addContent(Document document) throws DocumentException {
        String file = this.documentService.getDocumentById(id).getContent();
        Paragraph par = new Paragraph(file);
        document.add(par);
    }
}
