import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private SERVER_URL = "http://localhost:8080";

  constructor(private httpClient: HttpClient) {
  }

  public getAll() {
    return this.httpClient.get(this.SERVER_URL + '/api/user/getAll')
  }
}
