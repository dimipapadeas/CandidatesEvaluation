import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

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

  constructor(private httpClient: HttpClient) {
  }

  getAllFiltered(sort: string, page: string, size: string, description: string, type: string, userID: string) {
    return this.httpClient.get(
      environment.apiUrl + '/transactions/getAllFiltered', {
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
      environment.apiUrl + '/transactions/getAllForAccount', {
        observe: 'response',
        params: new HttpParams()
          .set('sort', sort)
          .set('page', page)
          .set('size', size)
          .set('accountId', accountId)
      })
  }

  deleteTransaction(transactionId) {
    return this.httpClient.delete(`${environment.apiUrl + '/transactions/delete'}/${transactionId}`);
  }

  updateTransaction(transaction: Transaction): Observable<Transaction> {
    return this.httpClient.put<Transaction>(`${environment.apiUrl + '/transactions/update'}`, transaction);
  }

  createDraftTransaction(accountId) {
    return this.httpClient.get<Transaction>(`${environment.apiUrl + '/transactions/createDraftTransaction'}/${accountId}`)
  }

  getTransactionById(transactionId: any) {
    return this.httpClient.get<Transaction>(`${environment.apiUrl + '/transactions/getTransactionIdById'}/${transactionId}`)
  }
}
