package com.hazem.clientproject.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="document")
public class Document implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_document")
        private int idDocument;  
	@Column(name="titre_doc")
        private String titreDoc;
	@Column(name="description")	
	private String descriptionDoc;
	@Column(name="chemin")	
	private String chemin;
	@ManyToOne
	@JoinColumn(name="article_id_article")
	private Article article;
	
	public int getIdDocument() {
		return idDocument;
	}
	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}
	public String getTitreDoc() {
		return titreDoc;
	}
	public void setTitreDoc(String titreDoc) {
		this.titreDoc = titreDoc;
	}
	public String getDescriptionDoc() {
		return descriptionDoc;
	}
	public void setDescriptionDoc(String descriptionDoc) {
		this.descriptionDoc = descriptionDoc;
	}
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}


}
