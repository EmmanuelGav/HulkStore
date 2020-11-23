import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Product } from './product.model';

const baseUrl = environment.url + "/product";
@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(
    private http: HttpClient) { }

  getAll(): Observable<Product[]> {
    return this.http.get<Product[]>(baseUrl + '/all')
      .pipe(
        catchError(this.handleError<Product[]>(baseUrl + '/all', []))
      );
  }
  getAllAvailable(): Observable<Product[]> {
    return this.http.get<Product[]>(baseUrl + '/all-available')
      .pipe(
        catchError(this.handleError<Product[]>(baseUrl + '/all-available', []))
      );
  }

  add(product: Product) {
    return this.http.post<Product>(baseUrl + '/add', product)
      .pipe(
        catchError(this.handleError<Product>('add'))
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
