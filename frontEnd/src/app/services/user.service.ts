import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

export interface User {
  id: string;
  userName: string;
  salt: string;
  pass: string;
  fistName: string;
  surName: string;
  comments: string;
  superAdmin: boolean;
  permissions: any[];
}

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private httpClient: HttpClient) {
  }

  public getAll() {
    return this.httpClient.get(
      environment.apiUrl + '/user/getAll', {
        observe: 'response'
      })
  }

  getUserById(userId: any) {
    return this.httpClient.get<User>(`${environment.apiUrl + '/user/getUserById'}/${userId}`, {
      observe: 'response'
    })
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.post<User>(`${environment.apiUrl + '/user/update'}`, user);
  }

  deleteUser(userId: any) {
    return this.httpClient.delete(`${environment.apiUrl + '/user/delete'}/${userId}`);
  }

  createDraftUser() {
    return this.httpClient.get<User>(`${environment.apiUrl + '/user/createDraftUser'}`, {
      observe: 'response'
    })
  }
}
