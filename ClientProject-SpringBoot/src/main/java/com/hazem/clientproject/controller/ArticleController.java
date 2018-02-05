package com.hazem.clientproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

import com.hazem.clientproject.entity.Article;
import com.hazem.clientproject.service.IArticleService;

@Controller
@RequestMapping("user")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ArticleController {
	@Autowired
	private IArticleService articleService;
	@GetMapping("article")
	public ResponseEntity<Article> getArticleById(@RequestParam("id") String id) {
		Article article = articleService.getArticleById(Integer.parseInt(id));
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@GetMapping("all-articles")
	public ResponseEntity<List<Article>> getAllArticles() {
		List<Article> list = articleService.getAllArticles();
		return new ResponseEntity<List<Article>>(list, HttpStatus.OK);
	}
	@PostMapping("article")
	public ResponseEntity<Void> createArticle(@RequestBody Article article, UriComponentsBuilder builder) {
		boolean flag = articleService.createArticle(article);
		if (flag == false) {
		     return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/article?id={id}").buildAndExpand(article.getArticleId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@GetMapping("article-by-title")
	public ResponseEntity<Article> getArticleByTitle(@RequestParam("title") String title) {
		Article art= articleService.getArticleByTitle(title);
		return new ResponseEntity<Article>(art, HttpStatus.OK);
	}

	@PutMapping("article")
	public ResponseEntity<Article> updateArticle(@RequestBody Article article) {
		articleService.updateArticle(article);
		return new ResponseEntity<Article>(article, HttpStatus.OK);
	}
	@DeleteMapping("article")
	public ResponseEntity<Void> deleteArticle(@RequestParam("id") String id) {
		articleService.deleteArticle(Integer.parseInt(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 