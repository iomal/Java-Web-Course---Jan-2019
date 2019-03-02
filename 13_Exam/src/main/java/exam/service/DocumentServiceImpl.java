package exam.service;

import exam.domain.entities.Document;
import exam.domain.models.service.DocumentServiceModel;
import exam.repository.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class DocumentServiceImpl implements DocumentService,Serializable {
    private DocumentRepository documentRepository;
    private ModelMapper modelMapper;

    public DocumentServiceImpl() {
    }

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel) {
        Document document = this.documentRepository.save(this.modelMapper.map(documentServiceModel,
                Document.class));
        return this.modelMapper.map(document,DocumentServiceModel.class);
    }

    @Override
    public DocumentServiceModel getDocumentById(String documentId) {
        return modelMapper.map(this.documentRepository.findById(documentId), DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> getAllDocuments() {
        return Arrays.asList(modelMapper.map(this.documentRepository.findAll(), DocumentServiceModel[].class));
    }

    @Override
    public void deleteDocument(String documentId) {
        this.documentRepository.delete(documentId);
    }
}
