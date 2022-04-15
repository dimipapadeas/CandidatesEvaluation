import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError} from "rxjs/operators";
import {throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BasicAuthHtppInterceptorService implements HttpInterceptor {

  constructor() {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
      req = req.clone({
        setHeaders: {
          Authorization: 'SBAFront' + sessionStorage.getItem('token')
        }
      })
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMsg = '';
        if (error.error instanceof ErrorEvent) {
          console.log('This is client side error');
          errorMsg = `Error: ${error.error.message}`;
        } else {
          console.log('This is server side error');
          errorMsg = `Error Code: ${error.status},  Message: ${error.message} ,  Trace: ${error.error.trace}`;
        }
        console.log(errorMsg);
        return throwError(errorMsg);
      })
    )

  }
}
