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
          let tokenStr = userData.jwttoken;
          let userId = userData.userId;
          sessionStorage.setItem("token", tokenStr);
          sessionStorage.setItem("userId", userId);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let toekn = sessionStorage.getItem("token");
    return !(toekn === null);
  }

  logOut() {
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("token");
  }
}
