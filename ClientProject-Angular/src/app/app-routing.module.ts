import { Routes, RouterModule } from '@angular/router';
import { ArticleComponent } from './article.component';
import { HomeComponent } from './home.component';
import { LoginComponent } from './login.component';
import {	Signup } from './signup';
import { CanActivateAuthGuard } from './can-activate.authguard';

const routes: Routes = [
	{
    path: 'article',
    component: ArticleComponent,canActivate: [CanActivateAuthGuard]
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
   {
    path: 'signup',
    component: Signup,
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];
export const appRouterModule = RouterModule.forRoot(routes);