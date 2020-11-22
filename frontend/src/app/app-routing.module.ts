import {Router, RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {LoginComponent} from './login/login.component';
import {SignupComponent} from './signup/signup.component';
import {TeamLeaderComponent} from './team-leader/team-leader.component';
import {AllProjectsComponent} from './all-projects/all-projects.component';
import {MyProjectsComponent} from './my-projects/my-projects.component';
import {LogsComponent} from './logs/logs.component';
import {UploadComponent} from './upload/upload.component';
import {LeaderGuardService} from '../service/leader.guard.service';
import {MemberGuardService} from '../service/member.guard.service';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'upload', component: UploadComponent},
  {path: 'members', component: TeamLeaderComponent, canActivate: [LeaderGuardService]},
  {path: 'projects', component: AllProjectsComponent, canActivate: [LeaderGuardService]},
  {path: 'my-projects', component: MyProjectsComponent, canActivate: [MemberGuardService]},
  {path: 'logs', component: LogsComponent, canActivate: [MemberGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(private router: Router) {}
}
