package exam.service;

import exam.domain.models.service.DocumentServiceModel;

import java.util.List;

public interface DocumentService {
    DocumentServiceModel scheduleDocument (DocumentServiceModel documentServiceModel);

    DocumentServiceModel getDocumentById(String documentId);

    List<DocumentServiceModel> getAllDocuments();

    void deleteDocument (String documentId);
}
