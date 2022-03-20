import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {environment} from "../../environments/environment";

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
      .post<any>(environment.apiUrl + "/authenticate", {username, password})
      .pipe(
        map(userData => {
          sessionStorage.setItem("username", username);
          sessionStorage.setItem("token", userData.jwttoken);
          sessionStorage.setItem("userId", userData.userId);
          sessionStorage.setItem("userAdmin", userData.admin);
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
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("userAdmin");
  }
}
