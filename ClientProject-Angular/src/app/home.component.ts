import { Component,OnInit } from '@angular/core';
import { TranslateService } from './translate';

@Component({
  selector: 'home',
  template: `<h1>{{ 'Welcome' | translate }}</h1>
  
    <div>
        <!--translate with pipe-->
        <p>
            Translate With Pipe: <strong>{{ 'Welcome' | translate }}</strong>
        </p>

        <!--reanslate with service-->
        <p>
            Translate with Service: <strong>{{ translatedText }}</strong>
        </p>
    </div>

  `
})
export class HomeComponent implements OnInit {
	 /* public translatedText: string;
		public supportedLangs: any[];

    constructor(private _translate: TranslateService) { } */

    ngOnInit() {
       /*  // standing data
        this.supportedLangs = [
        { display: 'English', value: 'en' },
        { display: 'Français', value: 'fr' },
        ];

        // set current langage
        this.selectLang('en'); */
    }

/*     isCurrentLang(lang: string) {
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
    } */
}
