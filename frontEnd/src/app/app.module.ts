import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HomeComponent} from './home/home.component';
import {AboutComponent} from './about/about.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import {MatCardModule} from "@angular/material/card";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {UserComponent} from './user/user.component';
import {MatButtonModule} from "@angular/material/button";
import {EditUserComponent} from './user/edit-user/edit-user.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BasicAuthHtppInterceptorService} from "./services/basic-auth-htpp-interceptor.service";
import {LoginComponent} from './login/login.component';
import {MatMenuModule} from '@angular/material/menu';
import {NewUserComponent} from './user/new-user/new-user.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AboutComponent,
    UserComponent,
    EditUserComponent,
    LoginComponent,
    NewUserComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    ReactiveFormsModule,
    FormsModule,
    MatMenuModule,
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: BasicAuthHtppInterceptorService, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
