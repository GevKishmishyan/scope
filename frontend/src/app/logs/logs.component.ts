import {Component, OnInit, TemplateRef} from '@angular/core';
import {Log} from '../../model/log.model';
import {Project} from '../../model/project.model';
import {ProjectService} from '../../service/project.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MatTableDataSource} from '@angular/material/table';
import {LogService} from '../../service/log.service';
import {frontUrl} from '../../environments/environment';
import {Router} from '@angular/router';
import {User} from '../../model/user.model';
import {FormControl, FormGroup, Validators} from '@angular/forms';

declare var $: any;

@Component({
  selector: 'app-logs',
  templateUrl: './logs.component.html',
  styleUrls: ['./logs.component.css']
})
export class LogsComponent implements OnInit {

  displayedColumns: string[] = ['Id', 'Delete', 'Project Name', 'Date', 'Hours'];
  log: Log;
  myProjects: Project[] = [];
  logs: Log[] = [];
  dataSource = new MatTableDataSource();
  currentUser: User;
  formGroup: FormGroup;
  projects: Project[];

  constructor(private projectService: ProjectService, private modalService: NgbModal, private logService: LogService, private router: Router) {
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.initForm();
    this.projectService.getTeamMemberProject()
      .subscribe(data => {
        this.myProjects = data;
      });
    this.logService.getLogsPerUser()
      .subscribe(data => {
        this.dataSource = new MatTableDataSource(data);
      });
  }

  open(content: TemplateRef<any>): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }

  deleteLogs(): void {
    // tslint:disable-next-line:prefer-const
    let logsIds = [];
    // tslint:disable-next-line:typedef
    $('input.delete').each(function() {
      if ($(this).is(':checked')) {
        // tslint:disable-next-line:radix
        logsIds.push(parseInt(this.id));
      }
    });

    for (const id of logsIds) {
      this.logService.deleteById(id).subscribe(data => {
        console.log('Log with ' + id + ' deleted');
      });
    }
    document.location.reload(true);
  }

  logout(): void {
    localStorage.clear();
    window.location.href = `${frontUrl}`;
  }

  crateLog(): void {
    if (this.formGroup.valid) {
      this.logService.create(this.formGroup.value).subscribe(
        data => {
          console.log('Log successfully created');
          document.location.reload(true);
        });
    }
  }

  initForm(): void {
    this.projectService.getCurrentUserProjects().subscribe(data => {
      this.projects = data;
    });
    this.formGroup = new FormGroup({
      date: new FormControl('', [Validators.required]),
      projectId: new FormControl('', [Validators.required]),
      hours: new FormControl('', [Validators.required])
    });
  }
}
