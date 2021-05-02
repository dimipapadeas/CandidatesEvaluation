import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

export interface User {
  id: string;
  uName: string;
  salt: string;
  pass: string;
  fName: string;
  sName: string;
  comments: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private SERVER_URL = "http://localhost:8080/api/user/";

  constructor(private httpClient: HttpClient) {
  }

  public getAll() {
    return this.httpClient.get(this.SERVER_URL + 'getAll')
  }

  getUserById(paramId: any) {
    return this.httpClient.get<User>(`${this.SERVER_URL + 'getUserById'}/${paramId}`)
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.SERVER_URL + 'update'}`, user);
  }
}
