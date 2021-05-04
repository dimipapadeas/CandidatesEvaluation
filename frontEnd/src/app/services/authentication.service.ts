import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private httpClient: HttpClient) {
  }

// Provide username and password for authentication, and once authentication is successful,
//store JWT token in session
  authenticate(username, password) {
    return this.httpClient
      .post<any>("http://localhost:8080/api/authenticate", {username, password})
      .pipe(
        map(userData => {
          sessionStorage.setItem("username", username);
          let tokenStr = userData.token;
          sessionStorage.setItem("token", tokenStr);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    return !(user === null);
  }

  logOut() {
    let username = sessionStorage.getItem("username");
    this.httpClient.delete(`http://localhost:8080/api/deleteToken/${username}`).subscribe((data: any[]) => {
      sessionStorage.removeItem("username");
      sessionStorage.removeItem("token");
    });
  }
}
