import {Component, EventEmitter, OnInit} from '@angular/core';
import {frontUrl} from '../../environments/environment';
import {User} from '../../model/user.model';
import {UserService} from '../../service/user.service';
import {DomSanitizer} from '@angular/platform-browser';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  currentUser: User;
  file: File;

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('user'));
  }

  logout(): void {
    localStorage.clear();
    window.location.href = `${frontUrl}`;
  }

  onFileSelected(event): void {
    if (event.target.files && event.target.files.length) {
      this.file = event.target.files[0];
    }
  }

  upload(): void {
    const formData = new FormData();
    formData.append('file', this.file);
    this.userService.uploadProfilePicture(formData).subscribe(data => {
      const user = JSON.parse(localStorage.getItem('user'));
      user.profilePicture = data.name;
      localStorage.setItem('user', JSON.stringify(user));
    });
  }
}
