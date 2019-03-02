package exam.web.beans;

import exam.domain.models.binding.ScheduleCreateBindingModel;
import exam.domain.models.service.DocumentServiceModel;
import exam.domain.models.view.DocumentViewModel;
import exam.service.DocumentService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Named("list")
@RequestScoped
public class DocumentListBean {
    private List<DocumentViewModel> model;
    private ModelMapper modelMapper;
    private DocumentService documentService;

    public DocumentListBean() {
    }

    @Inject
    public DocumentListBean(ModelMapper modelMapper, DocumentService documentService) {
        this.modelMapper = modelMapper;
        this.documentService = documentService;
    }

    @PostConstruct
    public void init() {
        this.model = this.documentService.getAllDocuments().stream()
                .map(d -> modelMapper.map(d, DocumentViewModel.class))
                .map(d -> {
                    if(d.getTitle().length()>12)
                    d.setTitle(d.getTitle().substring(0, 12) + "...");
                    return d;
                }).collect(Collectors.toList());
    }

    public List<DocumentViewModel> getModel() {
        return this.model;
    }

    public void setModel(List<DocumentViewModel> model) {
        this.model = model;
    }


}
