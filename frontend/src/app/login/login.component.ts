import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {Role} from '../../model/role.enum';
import {frontUrl} from '../../environments/environment';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  formGroup: FormGroup;

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.formGroup = new FormGroup({
      email: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required])
    });
  }

  loginProcess(): void {
    if (this.formGroup.valid) {
      this.authService.login(this.formGroup.value).subscribe(
        data => {
          localStorage.setItem('token', data.token);
          localStorage.setItem('user', JSON.stringify(data.userRegisterResponse));
          if (data.userRegisterResponse.role === Role.TEAM_LEADER) {
            window.location.href = `${frontUrl}projects`;
          } else {
            window.location.href = `${frontUrl}logs`;
          }
        });
    }
  }
}
