import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "./user.service";
import {Observable} from "rxjs";

export interface Account {
  id: string;
  calculatedBalance: number;
  description: String;
  lastTransaction: Date;
  users: [User];
}

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private SERVER_URL = "http://localhost:8080/api/account/";

  constructor(private httpClient: HttpClient) {
  }

  getByUsername(userID: string) {
    return this.httpClient.get(
      this.SERVER_URL + 'getByUsername', {
        observe: 'response',
        params: new HttpParams()
          .set('userID', userID)
      })
  }

  getAccountById(accountId: any) {
    return this.httpClient.get<Account>(`${this.SERVER_URL + 'getAccountById'}/${accountId}`)
  }

  createDraftAccount() {
    return this.httpClient.get<Account>(`${this.SERVER_URL + 'draftAccount'}`)
  }

  updateAccount(account: Account): Observable<Account> {
    return this.httpClient.put<Account>(`${this.SERVER_URL + 'update'}`, account);
  }
}
