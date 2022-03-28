import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AboutService {

  constructor(private httpClient: HttpClient) {
  }

  getVersion() {
    return this.httpClient.get(
      environment.apiUrl + '/info/getVersion', {
        observe: 'response',
      })
  }
}
