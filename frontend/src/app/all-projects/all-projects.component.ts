import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user.service';
import {User} from '../../model/user.model';
import {ProjectService} from '../../service/project.service';
import {MatTableDataSource} from '@angular/material/table';
import {Project} from '../../model/project.model';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {frontUrl} from '../../environments/environment';
import {NavigationEnd, Router} from '@angular/router';

declare var $: any;

@Component({
  selector: 'app-all-projects',
  templateUrl: './all-projects.component.html',
  styleUrls: ['./all-projects.component.css']
})
export class AllProjectsComponent implements OnInit {
  displayedColumns: string[] = ['Id', 'Delete', 'Project Name', 'Start Date', 'Deadline', 'Members', 'Hours'];
  users: User[] = [];
  currentUser: User;
  projects: Project[] = [];
  project: Project;
  dataSource = new MatTableDataSource();
  formGroup: FormGroup;

  constructor(private userService: UserService, private projectService: ProjectService, private modalService: NgbModal, private router: Router) {
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  open(content): void {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.initForm();
    this.project = new Project();
    this.projectService.getAllProject().subscribe(data => {
      this.dataSource = new MatTableDataSource(data);
    });
  }

  initForm(): void {
    this.userService.getAllTeamMembers().subscribe(data => {
      this.users = data;
    });
    this.formGroup = new FormGroup({
      name: new FormControl('', [Validators.required]),
      date: new FormControl('', [Validators.required]),
      deadline: new FormControl('', [Validators.required]),
      membersId: new FormControl('', [Validators.required])
    });
  }

  deleteProjects(): void {
    // tslint:disable-next-line:prefer-const
    let projectIds = [];
    // tslint:disable-next-line:typedef
    $('input.delete').each(function() {
      if ($(this).is(':checked')) {
        // tslint:disable-next-line:radix
        projectIds.push(parseInt(this.id));
      }
    });

    for (const id of projectIds) {
      this.projectService.deleteById(id).subscribe(data => {
        console.log('Project with ' + id + ' deleted');
      });
    }
    document.location.reload(true);
  }

  crateProject(): void {
    if (this.formGroup.valid) {
      this.projectService.create(this.formGroup.value).subscribe(
        data => {
          console.log('Project successfully created');
          document.location.reload(true);
        });
    }
  }

  logout(): void {
    localStorage.clear();
    window.location.href = `${frontUrl}`;
  }
}
