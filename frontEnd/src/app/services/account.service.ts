import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";

export interface Account {
  id: string;
  calculatedBalance: number;
  description: String;
}

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private SERVER_URL = "http://localhost:8080/api/account/";

  constructor(private httpClient: HttpClient) {
  }

  getAllFiltered(userID: string) {
    return this.httpClient.get(
      this.SERVER_URL + 'getAllFiltered', {
        observe: 'response',
        params: new HttpParams()
          .set('userID', userID)
      })
  }

  getAccountById(accountId: any) {
    return this.httpClient.get<Account>(`${this.SERVER_URL + 'getAccountById'}/${accountId}`)
  }
}
