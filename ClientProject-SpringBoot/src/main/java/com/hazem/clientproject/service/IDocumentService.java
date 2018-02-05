package com.hazem.clientproject.service;

import java.util.List;

import com.hazem.clientproject.entity.Document;

public interface IDocumentService {

	List<Document> getAllDocuments();
    Document getDocumentById(int idDocument);
    boolean createDocument(Document document);
    void updateDocument(Document document);
    void deleteDocument(int idDocuemnt);
}
