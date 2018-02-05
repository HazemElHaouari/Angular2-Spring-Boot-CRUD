package com.hazem.clientproject.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hazem.clientproject.entity.Document;

@Transactional
@Repository
public class DocumentDAO implements IDocumentDAO{

	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Document getDocumentById(int idDocument) {
		return entityManager.find(Document.class, idDocument);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Document> getAllDocuments() {
		String hql = "FROM Document as doc ORDER BY doc.idDocument DESC";
		return (List<Document>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void createDocument(Document document) {
		entityManager.persist(document);
	}
	@Override
	public void updateDocument(Document document) {
		Document doc = getDocumentById(document.getIdDocument());
		doc.setTitreDoc(document.getTitreDoc());
		doc.setDescriptionDoc(document.getDescriptionDoc());
		doc.setChemin(document.getChemin());
		doc.setArticle(document.getArticle());
		entityManager.flush();
	}
	
	@Override
	public void deleteDocument(int idDocument) {
		entityManager.remove(getDocumentById(idDocument));
	}
	@Override
	public boolean documentExists(String titreDoc) {
		String hql = "FROM Document as doc WHERE doc.titreDoc = ?";
		int count = entityManager.createQuery(hql).setParameter(1, titreDoc).getResultList().size();
		return count > 0 ? true : false;
	}

}
