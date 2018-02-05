package com.hazem.clientproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazem.clientproject.dao.IArticleDAO;
import com.hazem.clientproject.entity.Article;

@Service
public class ArticleService implements IArticleService {
	@Autowired
	private IArticleDAO articleDAO;
	@Override
	public Article getArticleById(int articleId) {
		Article obj = articleDAO.getArticleById(articleId);
		return obj;
	}	
	@Override
	public List<Article> getAllArticles(){
		return articleDAO.getAllArticles();
	}
	@Override
	public Article getArticleByTitle(String title) {
		List<Article> arts=articleDAO.getAllArticles();
		int i=0;
		Article article= new Article();
		while(i<arts.size()){
			if(title.equals(arts.get(i).getTitle())){
				
				article=arts.get(i);
				i=arts.size();
			}
			i++;
		}
		return article ;
	}
	@Override
	public synchronized boolean createArticle(Article article){
               if (articleDAO.articleExists(article.getTitle(), article.getCategory())) {
    	           return false;
               } else {
    	           articleDAO.createArticle(article);
    	           return true;
               }
	}
	@Override
	public void updateArticle(Article article) {
		articleDAO.updateArticle(article);
	}
	@Override
	public void deleteArticle(int articleId) {
		articleDAO.deleteArticle(articleId);
	}
} 
