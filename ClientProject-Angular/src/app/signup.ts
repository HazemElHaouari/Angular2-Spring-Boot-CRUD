import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs';

import { User } from './user';
import { AuthenticationService } from './authentication.service';

@Component({
	selector: 'signup',
    templateUrl: './signup.html',
    styleUrls: ['./signup.css']
})
export class Signup {
  constructor(public router: Router, public http: Http,private authenticationService: AuthenticationService) {
  }
  statusCode: number;
  userUrl = "http://localhost:8080/auth/signup";
  private headers = new Headers({
     'Content-Type': 'application/json' });

  signup(event, username, password,firstname,lastname,email) {
    event.preventDefault();
    let user = new User(null,username,password,firstname,lastname,email );
    this.createUser(user).subscribe(successCode => {
		              this.statusCode = successCode;
					  /* this.authenticationService.login(username,password)
					.subscribe(result => {
						// login successful
						this.router.navigate(['home']);
						
            }, errorCode =>this.statusCode = errorCode); */
			},
		        errorCode => this.statusCode = errorCode);
  }

  login(event) {
    event.preventDefault();
    this.router.navigate(['login']);
  }
	    //Create user
    createUser(user: User):Observable<number> {
	let cpHeaders = new Headers({ 'Content-Type': 'application/json'});
        let options = new RequestOptions({ headers: cpHeaders });
        return this.http.post(this.userUrl, user, options)
               .map(success => success.status)
               .catch(this.handleError);
    }
	private handleError (error: Response | any) {
	console.error(error.message || error);
	return Observable.throw(error.status);
    }
	/* this.http.post('http://localhost:8080/user/signup', body, { headers: this.headers })
      .subscribe(
        response => {
          sessionStorage.setItem('currentUser', response.json().token);
          this.router.navigate(['home']);
        },
        error => {
          alert(error.text());
          console.log(error.text());
        }
      ); */
}