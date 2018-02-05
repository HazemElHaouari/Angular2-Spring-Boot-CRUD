import { Injectable } from '@angular/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { Article } from './article';
import{ Document } from './document';
import { AuthenticationService } from './authentication.service';

@Injectable()
export class ArticleService {
    //URLs for CRUD operations
    allArticlesUrl = "http://localhost:8080/user/all-articles";
    articleUrl = "http://localhost:8080/user/article";
	allDocumentsUrl = "http://localhost:8080/user/all-documents";
    documentUrl = "http://localhost:8080/user/document";
	articleByTitleUrl = "http://localhost:8080/user/article-by-title";
	private headers = new Headers({
     'Content-Type': 'application/json',
     'Authorization': 'Bearer ' + this.authenticationService.getToken()
     });

    //Create constructor to get Http instance
    constructor(private http:Http,private authenticationService: AuthenticationService) { 
    }
    //Fetch all articles
    getAllArticles(): Observable<Article[]> {
        return this.http.get(this.allArticlesUrl, {headers: this.headers})
	       .map(this.extractData)
	       .catch(this.handleError);

    }
	//Fetch all documents
    getAllDocuments(): Observable<Document[]> {
        return this.http.get(this.allDocumentsUrl,{headers: this.headers})
	       .map(this.extractData)
	       .catch(this.handleError);

    }
    //Create article
    createArticle(article: Article):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json','Authorization': 'Bearer ' + this.authenticationService.getToken() });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.articleUrl, article, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
	//Create document
    createDocument(documentArt: Document):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json','Authorization': 'Bearer ' + this.authenticationService.getToken() });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.documentUrl, documentArt, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
	//Fetch Article by title
    getArticleByTitle(title: string): Observable<Article> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json','Authorization': 'Bearer ' + this.authenticationService.getToken() });
	let cpParams = new URLSearchParams();
	cpParams.set('title', title);			
	let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
	return this.http.get(this.articleByTitleUrl, options)
		.map(this.extractData)
		.catch(this.handleError);
    }
    //Fetch article by id
    getArticleById(articleId: string): Observable<Article> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json','Authorization': 'Bearer ' + this.authenticationService.getToken() });
	let cpParams = new URLSearchParams();
	cpParams.set('id', articleId);			
	let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
	return this.http.get(this.articleUrl, options)
		.map(this.extractData)
		.catch(this.handleError);
    }	
    //Update article
    updateArticle(article: Article):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json','Authorization': 'Bearer ' + this.authenticationService.getToken() });
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.put(this.articleUrl, article, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
    //Delete article	
    deleteArticleById(articleId: string): Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json','Authorization': 'Bearer ' + this.authenticationService.getToken() });
	let cpParams = new URLSearchParams();
	cpParams.set('id', articleId);			
	let options = new RequestOptions({ headers: cpHeaders, params: cpParams });
	return this.http.delete(this.articleUrl, options)
	       .map(success => success.status)
	       .catch(this.handleError);
    }		
    private extractData(res: Response) {
	let body = res.json();
        return body;
    }
    private handleError (error: Response | any) {
	console.error(error.message || error);
	return Observable.throw(error.status);
    }
} 