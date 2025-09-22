import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';
import { passwordMatchValidator } from './validation';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  userForm!: FormGroup;
  errorMessage: string = '';
  personalTrainerId: string | null = null;
  roleParam: string | null = null; // guarda o role recebido

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
      phone: ['', Validators.required],
      gender: [''],
      role: ['', Validators.required],
      dateOfBirth: ['', Validators.required]
    }, { validators: passwordMatchValidator });

    // Captura o role vindo do query param
    this.route.queryParams.subscribe(params => {
      const role = params['role'];
      if (role) {
        this.roleParam = role;
        this.userForm.get('role')?.setValue(role);
        this.userForm.get('role')?.disable(); // trava o campo
      }

      // Se o usuário é Personal Trainer logado e vai cadastrar Athlete
      if (role === 'ATHLETE') {
        this.personalTrainerId = params['trainerId'] || null;
      }
    });
  }

  onSubmit() {
    if (this.userForm.invalid) {
      this.userForm.markAllAsTouched();
      this.errorMessage = 'Please fill all required fields correctly.';
      return;
    }

    const userData: any = {
      email: this.userForm.get('email')?.value,
      password: this.userForm.get('password')?.value,
      role: this.userForm.getRawValue().role, // getRawValue() pega mesmo desabilitado
      firstName: this.userForm.get('firstName')?.value,
      lastName: this.userForm.get('lastName')?.value,
      phone: this.userForm.get('phone')?.value,
      gender: this.userForm.get('gender')?.value,
      dateOfBirth: this.userForm.get('dateOfBirth')?.value,
    };

    // Se for atleta, adiciona o personalTrainerId
    if (userData.role === 'ATHLETE' && this.personalTrainerId) {
      userData.personalTrainerId = this.personalTrainerId;
    }

    this.authService.register(userData).subscribe({
      next: () => {
        this.errorMessage = '';
        if (userData.role === 'ATHLETE') {
          // Criado pelo treinador logado → dashboard
          this.router.navigate(['/personaltrainer/athletes'], {
            state: { successMessage: 'Athlete created successfully!' } 
          });
        } else {
          // Novo personal trainer → login
          this.router.navigate(['/login']);
        }
      },
      error: (err) => {
        console.error('Error on register:', err);
        this.errorMessage = 'Failed to register. Please check your data and try again.';
      }
    });
  }

  login() {
    this.router.navigate(['/login']);
  }
}
