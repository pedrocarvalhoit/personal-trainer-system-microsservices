import { Component } from '@angular/core';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';
import { FormsModule } from '@angular/forms';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { Router } from '@angular/router';
import { BioimpedanceAssessment, BioimpedanceAssessmentService } from '../../../core/service/bioimpedance-assessment/bioimpedance-assessment.service';


@Component({
  selector: 'app-create-bioimpedance-assessment',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './create-bioimpedance-assessment.component.html',
  styleUrl: './create-bioimpedance-assessment.component.scss'
})
export class CreateBioimpedanceAssessmentComponent {

  athletes: Athlete[] = [];
  selectedAthlete: Athlete | null = null;
  age: number | undefined;
  height: number | undefined;
  bodyWeight: number | undefined;
  bmi: number | undefined;
  bodyFatPercentual: number | undefined;
  bodyMassPercentual: number | undefined;
  basalMetabolismRateCalories: number | undefined;
  visceralFatIndice: number | undefined;
  idealWeight: number | undefined;
  idealBodyFatPercentual: number | undefined;
  notes: string | undefined;

  constructor(
        private router: Router,
        private bioimpedanceAssessmentService: BioimpedanceAssessmentService,
        private authService: AuthService,
        private userService: UserService
      ) {}
  
  ngOnInit(): void {
    this.loadAthletes();
  }  

  loadAthletes(): void {
      const token = this.authService.getToken();
      if (token) {
        const headers = new HttpHeaders({
          Authorization: `Bearer ${token}`,
        });
        this.userService.getAllEnabledAthletes(headers).subscribe(
          (response: PageResponse<Athlete>) => {
            this.athletes = response.content;
          },
          (error) => {
            console.error('Failed to load enabled clients:', error);
          }
        );
      }
    }

  onSubmit(): void {
   this.saveAssessment();
  }

  saveAssessment(): void {
    if (!this.selectedAthlete) {
      console.error('No athlete selected.');
      return;
    }
    const token = this.authService.getToken();
    const selectedAthleteId = this.selectedAthlete?.id;
    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`,
      });

      this.bioimpedanceAssessmentService
        .save(
          headers,
          selectedAthleteId,
          this.age || 0,
          this.height || 0,
          this.bodyWeight || 0,
          this.bmi || 0,
          this.bodyFatPercentual || 0,
          this.bodyMassPercentual || 0,
          this.basalMetabolismRateCalories || 0,
          this.visceralFatIndice || 0,
          this.idealWeight || 0,
          this.idealBodyFatPercentual || 0,
          this.notes || '',
        )
        .subscribe(
          (response) => {
            this.router.navigate([`personaltrainer/athlete-dashboard/${this.selectedAthlete?.id}`],{
              state: { successMessage: 'Bioimpedance Assessment created successfully!' } 
            });
          },
          (error) => {
            console.error('Create failed', error);
          }
        );
    } else {
      console.error('Token not found');
    }
  }

}
