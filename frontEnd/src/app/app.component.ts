import {Component} from '@angular/core';
import {AuthenticationService} from "./services/authentication.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private loginservice: AuthenticationService, private router: Router,) {
  }

  title = 'Spring Boot Angular Template';

  logout() {
    this.loginservice.logOut();
    this.router.navigate(['login']);
  }

  isloggedIn() {
    return this.loginservice.isUserLoggedIn();
  }

}
