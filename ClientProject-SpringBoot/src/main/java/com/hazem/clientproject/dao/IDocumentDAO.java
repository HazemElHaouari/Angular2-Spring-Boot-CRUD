package com.hazem.clientproject.dao;

import java.util.List;

import com.hazem.clientproject.entity.Document;

public interface IDocumentDAO {

	List<Document> getAllDocuments();
    Document getDocumentById(int idDocument);
    void createDocument(Document document);
    void updateDocument(Document document);
    void deleteDocument(int idDocument);
    boolean documentExists(String titreDoc);
}
