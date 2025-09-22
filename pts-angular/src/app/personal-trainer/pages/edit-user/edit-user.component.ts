import { AuthService } from './../../../auth/services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { UserService } from '../../../core/service/user/user.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-edit-user',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.scss']
})
export class EditUserComponent implements OnInit {
  userForm!: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  firstName: string = '';
  lastName: string = '';
  dateOfBirth: string = '';
  phone: string = '';
  gender: string = '';

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      phone: ['', Validators.required],
      gender: [''],
      dateOfBirth: ['', Validators.required]
    });

    this.loadUserData();
  }

  private loadUserData() {
    const token = this.authService.getToken();

     if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      this.userService.getCurrentUserData(headers).subscribe(
        user => {
          this.userForm.patchValue({
          firstName: user.firstName,
          lastName: user.lastName,
          phone: user.phone,
          gender: user.gender,
          dateOfBirth: user.dateOfBirth
        });
        },
        error => console.error('Failed to fetch user', error)
      );
    }
  }

  onSubmit() {
  if (this.userForm.invalid) {
    this.userForm.markAllAsTouched();
    return;
  }

  const token = this.authService.getToken(); // Obtém o token JWT
  if (!token) {
    console.error('Token not found');
    return;
  }

  const headers = new HttpHeaders({
    'Authorization': `Bearer ${token}`
  });

  // Pega os valores do formulário
  const formData = this.userForm.value;

  this.userService.updateUser(
    headers,
    formData.firstName,
    formData.lastName,
    formData.dateOfBirth,
    formData.phone,
    formData.gender
  ).subscribe(
    response => {
      this.router.navigate(['personaltrainer/dashboard'], {
        state: { successMessage: 'User ediet successfully!' } 
      });
    },
    error => {
      console.error('Update failed', error);
    }
  );
}


  goToDashboard(): void {
    this.router.navigate(['/personaltrainer/dashboard']);
  }
}
