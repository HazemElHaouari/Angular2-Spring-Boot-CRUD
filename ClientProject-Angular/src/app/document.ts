import { Article } from './article';
export class Document{
	   constructor(public idDocument: string,public titreDoc: string,public descriptionDoc: string,public chemin: string,public article: Article) { 
   }
}