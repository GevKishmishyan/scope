import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../model/user.model';
import {backUrl} from '../environments/environment';
import {Observable} from 'rxjs';
import {map} from 'rxjs/operators';

const token = localStorage.getItem('token');
// tslint:disable-next-line:variable-name
const headers_object = new HttpHeaders()
  .set('Authorization', 'Bearer ' + token)
  .set('Content-Type', 'application/json; charset=utf-8')
  .set('Access-Control-Allow-Origin', '*');

const url = `${backUrl}user/`;

@Injectable()
export class UserService {


  constructor(private http: HttpClient) {
  }

  private static getHeaders(): any {
    return {
      headers: headers_object
    };
  }

  getAllTeamMembers(): Observable<any> {
    return this.http.get<User[]>(url + 'team-members', UserService.getHeaders());
  }

  getAllUsers(): Observable<any> {
    return this.http.get<User[]>(url + 'all-users');
  }

  getById(id: number): Observable<any> {
    return this.http.get<User>(url + id);
  }

  deleteById(id: number): void {
    this.http.delete(url + id);
  }

  uploadProfilePicture(file: FormData): Observable<any> {
    return this.http.post(url + 'upload', file, {
      headers: {
        Authorization: 'Bearer ' + token
      }
    });
  }

}
