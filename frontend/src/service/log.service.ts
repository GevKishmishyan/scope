import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Project} from '../model/project.model';
import {Log} from '../model/log.model';
import {backUrl} from '../environments/environment';
import {Observable} from 'rxjs';

const token = localStorage.getItem('token');
// tslint:disable-next-line:variable-name
const headers_object = new HttpHeaders()
  .set('Authorization', 'Bearer ' + token)
  .set('Content-Type', 'application/json; charset=utf-8')
  .set('Access-Control-Allow-Origin', '*');


@Injectable()
export class LogService{

  url = `${backUrl}log/`;
  project: Log;

  constructor(private http: HttpClient) {
  }

  private static getHeaders(): any {
    return {
      headers: headers_object
    };
  }

  create(data): Observable<any>{
    return this.http.post(this.url + 'create', data, LogService.getHeaders());
  }

  getAllLogs(): Observable<any>{
    return this.http.get<Log[]>(this.url + 'all-logs', LogService.getHeaders());
  }

  getLogsPerUser(): Observable<any>{
    return this.http.get<Log[]>(this.url + 'logs-per-user', LogService.getHeaders());
  }

  getById(id: number): Observable<any>{
    return this.http.get<Log>(this.url + id, LogService.getHeaders());
  }

  deleteById(id: number): Observable<any>{
    return this.http.delete(this.url + `delete/${id}`, LogService.getHeaders());
  }

}
