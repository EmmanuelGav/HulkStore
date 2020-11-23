import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Cart } from './cart.model';

const baseUrl = environment.url + "/cart";
@Injectable({
  providedIn: 'root'
})
export class CartService {
  
  constructor(
    private http: HttpClient) { }

  getAll(): Observable<Cart[]> {
    return this.http.get<Cart[]>(baseUrl + '/all')
    .pipe(
      catchError(this.handleError<Cart[]>(baseUrl + '/all', []))
    );
  }

  add(cart: Cart) {
    return this.http.post<Cart>(baseUrl + '/add', cart)
    .pipe(
      catchError(this.handleError<Cart>('addCart'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
  
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
  
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
