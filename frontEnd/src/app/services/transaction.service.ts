import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

export interface Transaction {
  id: string;
  amount: number;
  date: Date;
  description: string;
  type: string;
  userName: string;
  accountName: string;
}

@Injectable({
  providedIn: 'root'
})
export class TransactionService {

  private SERVER_URL = "http://localhost:8080/api/transactions/";

  constructor(private httpClient: HttpClient) {
  }

  getAllFiltered(sort: string, page: string, size: string, description: string, type: string, userID: string) {
    return this.httpClient.get(
      this.SERVER_URL + 'getAllFiltered', {
        observe: 'response',
        params: new HttpParams()
          .set('sort', sort)
          .set('page', page)
          .set('size', size)
          .set('description', description)
          .set('type', type)
          .set('userID', userID)
      })
  }

  getAllForAccount(sort: string, page: string, size: string, accountId: string) {
    return this.httpClient.get(
      this.SERVER_URL + 'getAllForAccount', {
        observe: 'response',
        params: new HttpParams()
          .set('sort', sort)
          .set('page', page)
          .set('size', size)
          .set('accountId', accountId)
      })
  }

  deleteTransaction(transactionId) {
    return this.httpClient.delete(`${this.SERVER_URL + 'delete'}/${transactionId}`);
  }

  updateTransaction(transaction: Transaction): Observable<Transaction> {
    return this.httpClient.put<Transaction>(`${this.SERVER_URL + 'update'}`, transaction);
  }

  createDraftTransaction(accountId) {
    return this.httpClient.get<Transaction>(`${this.SERVER_URL + 'createDraftTransaction'}/${accountId}`)
  }

  getTransactionById(transactionId: any) {
    return this.httpClient.get<Transaction>(`${this.SERVER_URL + 'getTransactionIdById'}/${transactionId}`)
  }
}
