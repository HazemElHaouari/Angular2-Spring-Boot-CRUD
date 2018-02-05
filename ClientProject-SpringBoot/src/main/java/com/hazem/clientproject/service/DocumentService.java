package com.hazem.clientproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazem.clientproject.dao.IDocumentDAO;
import com.hazem.clientproject.entity.Document;

@Service
public class DocumentService implements IDocumentService{

	@Autowired
	private IDocumentDAO documentDAO;
	@Override
	public Document getDocumentById(int idDocument) {
		Document obj = documentDAO.getDocumentById(idDocument);
		return obj;
	}	
	@Override
	public List<Document> getAllDocuments(){
		return documentDAO.getAllDocuments();
	}
	@Override
	public synchronized boolean createDocument(Document document){
               if (documentDAO.documentExists(document.getTitreDoc())) {
    	           return false;
               } else {
    	           documentDAO.createDocument(document);
    	           return true;
               }
	}
	@Override
	public void updateDocument(Document document) {
		documentDAO.updateDocument(document);
	}
	@Override
	public void deleteDocument(int idDocument) {
		documentDAO.deleteDocument(idDocument);
	}

}
