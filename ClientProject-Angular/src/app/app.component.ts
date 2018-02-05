import { Component,OnInit,OnDestroy,HostListener } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { TranslateService } from './translate';

@Component({
   selector: 'app-root',
   template: `
	<h1>{{title}}</h1>
	<nav>
		<a routerLink="/home" routerLinkActive="active">Home</a>
		<a routerLink="/login" routerLinkActive="active">Login</a>
		<a routerLink="/signup" routerLinkActive="active">Sign Up</a>
		<a routerLink="/article" routerLinkActive="active">Article</a>
	</nav>

  <div class="btn-group">
        <button *ngFor="let lang of supportedLangs" (click)="selectLang(lang.value)" class="btn btn-default" [class.btn-primary]="isCurrentLang(lang.value)">
            {{ lang.display }}
        </button>
    </div>

	<router-outlet></router-outlet>
             `
})
export class AppComponent { 
		
		 public translatedText: string;
		public supportedLangs: any[];

    constructor(private _translate: TranslateService) { }

    ngOnInit() {
        // standing data
        this.supportedLangs = [
        { display: 'English', value: 'en' },
        { display: 'Français', value: 'fr' },
        ];

        // set current langage
        this.selectLang('en');
    }

    isCurrentLang(lang: string) {
        // check if the selected lang is current lang
        return lang === this._translate.currentLang;
    }

    selectLang(lang: string) {
        // set current lang;
        this._translate.use(lang);
        this.refreshText();
    }

    refreshText() {
        // refresh translation when language change
        this.translatedText = this._translate.instant('Welcome');
    }
		/* @HostListener('window:unload', [ '$event' ])
		unloadHandler(event) {
		// ...
		localStorage.removeItem('currentUser');
		}
		 @HostListener('window:beforeunload', [ '$event' ])
		beforeUnloadHander(event) {
			// ...
			window.open("logout url","log out","height=10,width=10,location=no,menubar=no,status=no,titlebar=no,toolbar=no",true);
		} */
} 
