import { Injectable } from '@angular/core';

import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { UserProfile } from './user-profile.model';

const baseUrl = environment.url + "/user";
@Injectable({
  providedIn: 'root'
})
export class UserProfileService {
  
  constructor(
    private http: HttpClient) { }

  save(userSaved) {
    return this.http.post<UserProfile>(baseUrl + '/save', userSaved)
    .pipe(
      catchError(this.handleError<UserProfile>('save user'))
    );
  }
  
  findById(id: any) {
    return this.http.get<UserProfile>(baseUrl + '/findById/'+id)
    .pipe(
      catchError(this.handleError<UserProfile>('save user'))
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
