import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { Router, RouterModule } from '@angular/router';
import { UserService } from '../../../core/service/user/user.service';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterModule, CommonModule, FormsModule],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  firstName: string = '';
  userPhotoUrl: string = '';
  visible: boolean = false;
  selectedFile: File | null = null;

  showEditOption: boolean = false; 
  isMyClientsRoute: boolean = false;

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      this.userService.getUserDataForNavbar(headers).subscribe(
        user => {
          this.firstName = user.firstName;
          this.userPhotoUrl = user.photo;
        },
        error => console.error('Failed to fetch user', error)
      );
    }
  }

  onFileSelected(event: any): void {
    this.selectedFile = event.target.files[0];
  }

  uploadPhoto(): void {
    if (!this.selectedFile) return;

    const token = this.authService.getToken();
    if (!token) return;

    const formData = new FormData();
    formData.append('file', this.selectedFile);

    // Não definir Content-Type manualmente
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    this.userService.updatePhoto(headers, formData).subscribe(
      response => {
        this.userPhotoUrl = response; // Assume que backend retorna URL da foto
        this.visible = false;
      },
      error => console.error('Error updating user photo:', error)
    );
  }

  showDialog(): void { this.visible = true; }
  hideDialog(): void { this.visible = false; }

  editData(): void { this.router.navigate(['/personaltrainer/edit']); }
  
  logout(): void {
    this.authService.logout();
    this.router.navigate(['']);
  }
}
