import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Ng2UploaderModule } from 'ng2-uploader';
import { RouterModule }   from '@angular/router';

import { AppComponent }  from './app.component';
import { ArticleComponent }  from './article.component';
import { ArticleService } from './article.service';
import { AuthenticationService } from './authentication.service';
import { appRouterModule } from "./app-routing.module";
import { HomeComponent } from './home.component';
import { LoginComponent } from './login.component';
import { CanActivateAuthGuard } from './can-activate.authguard';
import {	Signup } from './signup';
import { TRANSLATIONPROVIDERS, TranslatePipe, TranslateService }   from './translate';


@NgModule({
  imports: [     
        BrowserModule,
	HttpModule,
	FormsModule,                        
	ReactiveFormsModule,
	Ng2UploaderModule,
	appRouterModule
	
  ],
  declarations: [
        AppComponent,
	ArticleComponent,
	HomeComponent,
	LoginComponent,
	Signup,
	TranslatePipe
  ],
  providers: [
        ArticleService,
		AuthenticationService,
		CanActivateAuthGuard,TranslateService,TRANSLATIONPROVIDERS
  ],
  bootstrap: [
        AppComponent
  ]
})

export class AppModule { } 