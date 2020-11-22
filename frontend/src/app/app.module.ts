import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {TeamLeaderComponent} from './team-leader/team-leader.component';
import {AllProjectsComponent} from './all-projects/all-projects.component';
import {MDBBootstrapModule} from 'angular-bootstrap-md';
import {UserService} from '../service/user.service';
import {ProjectService} from '../service/project.service';
import {LogService} from '../service/log.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatFormField, MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import { MyProjectsComponent } from './my-projects/my-projects.component';
import { LogsComponent } from './logs/logs.component';
import {AuthService} from '../service/auth.service';
import { UploadComponent } from './upload/upload.component';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import {SelectModule} from 'ng-select';
import {NgOptionHighlightModule} from '@ng-select/ng-option-highlight';
import {LeaderGuardService} from '../service/leader.guard.service';
import {MemberGuardService} from '../service/member.guard.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    TeamLeaderComponent,
    AllProjectsComponent,
    MyProjectsComponent,
    LogsComponent,
    UploadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MDBBootstrapModule.forRoot(),
    NgbModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    NgMultiSelectDropDownModule.forRoot(),
    NgOptionHighlightModule,
    SelectModule,
  ],
  providers: [
    UserService,
    ProjectService,
    LogService,
    AuthService,
    LeaderGuardService,
    MemberGuardService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
