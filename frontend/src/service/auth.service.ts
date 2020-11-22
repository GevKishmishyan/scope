import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {backUrl} from '../environments/environment';

@Injectable()
export class AuthService{

  constructor(private http: HttpClient) {
  }

  login(data): Observable<any>{
    return this.http.post(`${backUrl}login`, data);
  }

  signup(data): Observable<any>{
    return this.http.post(`${backUrl}sign-up`, data);
  }

}
