import {MdbTableDirective, MdbTablePaginationComponent} from 'angular-bootstrap-md';
import {AfterViewInit, ChangeDetectorRef, Component, OnInit, ViewChild} from '@angular/core';
import {User} from '../../model/user.model';
import {UserService} from '../../service/user.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {frontUrl} from '../../environments/environment';

@Component({
  selector: 'app-team-leader',
  templateUrl: './team-leader.component.html',
  styleUrls: ['./team-leader.component.css'],
})
export class TeamLeaderComponent implements OnInit, AfterViewInit {

  @ViewChild(MdbTablePaginationComponent, {static: true}) mdbTablePagination: MdbTablePaginationComponent;
  @ViewChild(MdbTableDirective, {static: true}) mdbTable: MdbTableDirective;
  users: User[] = [];
  currentUser: User;
  previous: any = [];
  headElements = ['Id', 'Profile Picture', 'Name', 'Surname', 'Email'];

  constructor(private cdRef: ChangeDetectorRef, private userService: UserService, private router: Router) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    this.userService.getAllTeamMembers()
      .subscribe(data => {
        this.users = data;
      });
  }

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
