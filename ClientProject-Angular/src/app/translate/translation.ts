import { OpaqueToken } from '@angular/core';

// import translations
import { LANGENNAME, LANGENTRANS } from './lang-en';
import { LANGFRNAME, LANGFRTRANS } from './lang-fr';

// translation token
export const TRANSLATIONS = new OpaqueToken('translation');

// all translations
export const dictionary = {
    'en': LANGENTRANS,
    'fr' : LANGFRTRANS
};

// providers
export const TRANSLATIONPROVIDERS = [
    { provide: TRANSLATIONS, useValue: dictionary },
];