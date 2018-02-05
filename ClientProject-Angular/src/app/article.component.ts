import { Component, OnInit,AfterViewInit,
  EventEmitter,
  Input,
  Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Message} from 'primeng/primeng';
import { Router } from '@angular/router';

import { ArticleService } from './article.service';
import { Article } from './article';
import { Document } from './document';

@Component({
   selector: 'app-article',
   templateUrl: './article.component.html',
  // styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit,AfterViewInit { 
	@Input() elementId: String;
	@Output() onEditorKeyup = new EventEmitter<any>();

	editor;

	ngAfterViewInit() {
		tinymce.init({
      selector: 'textarea',
  height: 500,
  menubar: false,
  plugins: [
    "advlist autolink autosave save link image lists charmap print preview hr anchor pagebreak",
    "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
    "table contextmenu directionality emoticons template textcolor paste fullpage textcolor colorpicker textpattern"
  ],
  toolbar1: "newdocument fullpage | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect",
  toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent blockquote | undo redo | link unlink anchor image media code | insertdatetime preview | forecolor backcolor | save",
  toolbar3: "table | hr removeformat | subscript superscript | charmap emoticons | print fullscreen | ltr rtl | visualchars visualblocks nonbreaking template pagebreak restoredraft | currentdate | addname",
  toolbar_items_size: 'small',
  skin_url: 'assets/skins/lightgray',
  
       setup: function(editor) {
    
    function toTimeHtml(date) {
      return '<time datetime="' + date.toString() + '">' + date.toDateString() + '</time>';
    }
    
    function insertDate() {
      var html = toTimeHtml(new Date());
      editor.insertContent(html);
    }
	function toNameHtml(name){
		return '<h1>'+name+'</h1>';
	}
	function insertName() {
      var html = toNameHtml("client_name");
      editor.insertContent(html);
    }
    editor.addButton('currentdate', {
      icon: 'insertdatetime',
      //image: 'http://p.yusukekamiyamane.com/icons/search/fugue/icons/calendar-blue.png',
      tooltip: "Insert Current Date",
      onclick: insertDate
    });
	editor.addButton('addname', {
		text: 'add client name',
      //icon: 'insertdatetime',
      //image: 'http://p.yusukekamiyamane.com/icons/search/fugue/icons/calendar-blue.png',
      tooltip: "Insert name client",
      onclick: insertName
    });
  }
    });}
   //Component properties
   allArticles: Article[];
   allDocuments: Document[];
   documentArt: Document;
   msgs: Message[];
   statusCode: number;
   file: any;
   requestProcessing = false;
   articleIdToUpdate = null;
   processValidation = false;
   //Create form
   articleForm = new FormGroup({
       title: new FormControl('', Validators.required),
       category: new FormControl('', Validators.required)	   
   });
   //Create constructor to get service instance
   constructor(private articleService: ArticleService, private router: Router) {
   }
   //Create ngOnInit() and and load articles
   ngOnInit(): void {
	   this.getAllArticles();
   } 
	
   //Fetch all articles
   getAllArticles() {
        this.articleService.getAllArticles()
	   .subscribe(
                data => this.allArticles = data,
                errorCode =>  {
          this.router.navigate(['login']);
          console.error('An error occurred in dashboard component, navigating to login: ', errorCode); 
		this.statusCode = errorCode});   
   }
   //Fetch all documents
   getAllDocuments() {
        this.articleService.getAllDocuments()
	   .subscribe(
                data => this.allDocuments = data,
                errorCode =>  this.statusCode = errorCode);   
   }
   //Handle create and update article
   onArticleFormSubmit() {
	  this.processValidation = true;   
	  if (this.articleForm.invalid) {
	       return; //Validation failed, exit from method.
	  }   
	  //Form is valid, now perform create or update
          this.preProcessConfigurations();
	  let title = this.articleForm.get('title').value.trim();
          let category = this.articleForm.get('category').value.trim();	  
	  if (this.articleIdToUpdate === null) {  
	    //Handle create article
	    let article= new Article(null, title, category);	  
	    this.articleService.createArticle(article)
	      .subscribe(successCode => {
		              this.statusCode = successCode;
			      this.getAllArticles();
				  this.articleService.getArticleByTitle(title).subscribe(article =>{
					this.documentArt.article=article;
					this.articleService.createDocument(this.documentArt)
					.subscribe(successCode => {
		              this.statusCode = successCode;
			      this.getAllArticles();
					this.getAllDocuments();
			      this.backToCreateArticle();
			},
			errorCode => this.statusCode = errorCode);},
			errorCode => this.statusCode = errorCode);

			},
		        errorCode => this.statusCode = errorCode);
			/* for(var i=0;i<this.allArticles.length;i++){
					if(article.title===this.allArticles[i].title){
						this.documentArt.article=this.allArticles[i];
					} */
		
				

	  } else {  
   	    //Handle update article
	    let article= new Article(this.articleIdToUpdate, title, category);	  
	    this.articleService.updateArticle(article)
	      .subscribe(successCode => {
		        this.statusCode = successCode;
			      this.getAllArticles();	
			      this.backToCreateArticle();
			},
		        errorCode => this.statusCode = errorCode);	  
	  }
   }
   //Load article by id to edit
   loadArticleToEdit(articleId: string) {
      this.preProcessConfigurations();
      this.articleService.getArticleById(articleId)
	      .subscribe(article => {
		            this.articleIdToUpdate = article.articleId;   
		            this.articleForm.setValue({ title: article.title, category: article.category });
			    this.processValidation = true;
			    this.requestProcessing = false;   
		    },
		    errorCode =>  this.statusCode = errorCode);   
   }
   //Delete article
   deleteArticle(articleId: string) {
      this.preProcessConfigurations();
      this.articleService.deleteArticleById(articleId)
	      .subscribe(successCode => {
		      this.statusCode = successCode;
		      this.getAllArticles();	
		      this.backToCreateArticle();
		   },
		   errorCode => this.statusCode = errorCode);    
   }
   //Perform preliminary processing configurations
   preProcessConfigurations() {
          this.statusCode = null;
	  this.requestProcessing = true;   
   }
   //Go back from update to create
   backToCreateArticle() {
          this.articleIdToUpdate = null;
          this.articleForm.reset();	  
	  this.processValidation = false;
   }
   //upload file
   uploadFile: any;
  hasBaseDropZoneOver: boolean = false;
  options: Object = {
    url: 'http://localhost:8080/user/upload'
  };
  sizeLimit = 2000000;
 
  handleUpload(data): void {
    if (data && data.response) {
      data = JSON.parse(data.response);
      this.uploadFile = data;
	  
    }
  }
 
  fileOverBase(e:any):void {
    this.hasBaseDropZoneOver = e;
  }
  //get file name
  fileEvent(fileInput: any){
    let file = fileInput.target.files[0];
    let fileName = file.name;
	this.documentArt= new Document(null,fileName,null,"ressources/"+fileName,null);
}
 
  beforeUpload(uploadingFile): void {
    if (uploadingFile.size > this.sizeLimit) {
      uploadingFile.setAbort();
      alert('File is too large');
    }
  }
} 