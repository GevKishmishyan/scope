import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Project} from '../model/project.model';
import {backUrl} from '../environments/environment';
import {Observable} from 'rxjs';

const token = localStorage.getItem('token');
// tslint:disable-next-line:variable-name
const headers_object = new HttpHeaders()
  .set('Authorization', 'Bearer ' + token)
  .set('Content-Type', 'application/json; charset=utf-8')
  .set('Access-Control-Allow-Origin', '*');


@Injectable()
export class ProjectService {

  url = `${backUrl}project/`;
  project: Project;

  constructor(private http: HttpClient) {
  }

  private static getHeaders(): any {
    return {
      headers: headers_object
    };
  }

  create(data): Observable<any> {
    data.membersId = [parseInt(data.membersId)];
    return this.http.post(this.url + 'create', data, ProjectService.getHeaders());
  }

  getAllProject(): Observable<any> {
    return this.http.get<Project[]>(this.url + 'all-projects', ProjectService.getHeaders());
  }

  getTeamMemberProject(): Observable<any> {
    return this.http.get<Project[]>(this.url + 'team-member-projects', ProjectService.getHeaders());
  }

  getById(id: number): Observable<any> {
    return this.http.get<Project>(this.url + id, ProjectService.getHeaders());
  }

  getCurrentUserProjects(): Observable<any> {
    return this.http.get<Project[]>(this.url + 'current', ProjectService.getHeaders());
  }

  deleteById(id: number): Observable<any> {
    return this.http.delete(this.url + `delete/${id}`, ProjectService.getHeaders());
  }

}
