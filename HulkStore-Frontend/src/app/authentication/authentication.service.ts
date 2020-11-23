import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { UserProfile } from '../user-profile/user-profile.model';

const baseUrl = environment.url + "/auth";
@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    public currentUserProfileSubject: BehaviorSubject<UserProfile>;
    public currentUserProfile: Observable<UserProfile>;

    constructor(private http: HttpClient) {
        this.currentUserProfileSubject = new BehaviorSubject<UserProfile>(JSON.parse(localStorage.getItem('currentUserProfile')));
        this.currentUserProfile = this.currentUserProfileSubject.asObservable();
    }

    public get currentUserProfileValue(): UserProfile {
        return this.currentUserProfileSubject.value;
    }

    login(username: string, password: string) {
        return this.http.post<any>(`${baseUrl}/signin`, { username, password })
            .pipe(map(user => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentUserProfile', JSON.stringify(user));
                this.currentUserProfileSubject.next(user);
                return user;
            }));
    }

    register(username: string, email: string, password: string) {
        return this.http.post<any>(`${baseUrl}/signup`, {
            username, email, password
        }).pipe(map(response => {
            return response;
        }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUserProfile');
        this.currentUserProfileSubject.next(null);
    }
}