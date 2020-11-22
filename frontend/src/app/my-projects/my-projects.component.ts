import {ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {MdbTableDirective, MdbTablePaginationComponent} from 'angular-bootstrap-md';
import {User} from '../../model/user.model';
import {UserService} from '../../service/user.service';
import {LogService} from '../../service/log.service';
import {Log} from '../../model/log.model';
import {ProjectService} from '../../service/project.service';
import {Project} from '../../model/project.model';
import {frontUrl} from '../../environments/environment';
import {Router} from '@angular/router';

@Component({
  selector: 'app-my-projects',
  templateUrl: './my-projects.component.html',
  styleUrls: ['./my-projects.component.css']
})
export class MyProjectsComponent implements OnInit {

  @ViewChild(MdbTablePaginationComponent, {static: true}) mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  projects: Project[] = [];
  previous: any = [];
  currentUser: User;
  headElements = ['Id', 'Project Name', 'Start Date', 'Deadline', 'Hours'];

  constructor(private cdRef: ChangeDetectorRef, private projectService: ProjectService, private router: Router) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.projectService.getTeamMemberProject()
      .subscribe(data => {
        this.projects = data;
        console.log(data);
      });
  }

  // tslint:disable-next-line:use-lifecycle-interface
  ngAfterViewInit(): void {
    this.mdbTablePagination.setMaxVisibleItemsNumberTo(10);

    this.mdbTablePagination.calculateFirstItemIndex();
    this.mdbTablePagination.calculateLastItemIndex();
    this.cdRef.detectChanges();
  }

  logout(): void {
    localStorage.clear();
    window.location.href = `${frontUrl}`;
  }

}
