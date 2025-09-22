import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Athlete, UserService } from '../../../core/service/user/user.service';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-athlete-user',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './edit-athlete-user.component.html',
  styleUrl: './edit-athlete-user.component.scss'
})
export class EditAthleteUserComponent implements OnInit {
  athleteId!: string;
  athlete!: Athlete;

  userForm!: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.athleteId = this.route.snapshot.paramMap.get('athleteId')!;

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
      this.userService.getAthleteById(headers, this.athleteId).subscribe(
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

    const token = this.authService.getToken();
    if (!token) {
      console.error('Token not found');
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    // ✅ agora mandamos o objeto direto
    this.userService.updateAthlete(headers, this.athleteId, this.userForm.value).subscribe(
      () => {
        this.router.navigate(['personaltrainer/athletes'], {
          state: { successMessage: 'Athlete edited successfully!' }
        });
      },
      error => {
        console.error('Update failed', error);
        this.errorMessage = 'Update failed, try again.';
      }
    );
  }

  goToDashboard(): void {
    this.router.navigate(['/personaltrainer/dashboard']);
  }
}
