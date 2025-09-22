import { Component } from '@angular/core';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { InicialAssessmentService } from '../../../core/service/inicial-assessment/inicial-assessment.service';
import { HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from "../../../shared/components/navbar/navbar.component";
import { FooterComponent } from "../../../shared/components/footer/footer.component";
import { CommonModule } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';

@Component({
  selector: 'app-create-inicial-assessment',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './create-inicial-assessment.component.html',
  styleUrl: './create-inicial-assessment.component.scss'
})
export class CreateInicialAssessmentComponent {

  athletes: Athlete[] = [];
  selectedAthlete: Athlete | null = null;
  athleteAge: number | undefined;
  restingHeartRate: number | undefined;
  maxHeartRate: number | undefined;
  systolicBloodPressure: number | undefined;
  diastolicBloodPressure: number | undefined;
  sedentary: boolean | undefined;
  smoker: boolean | undefined;

  constructor(
    private router: Router,
    private inicialAssessmentService: InicialAssessmentService,
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

      this.inicialAssessmentService
        .save(
          headers,
          selectedAthleteId,
          this.athleteAge || 0,
          this.restingHeartRate || 0,
          this.systolicBloodPressure || 0,
          this.diastolicBloodPressure || 0,
          this.sedentary,
          this.smoker
        )
        .subscribe(
          (response) => {
            this.router.navigate([`personaltrainer/athlete-dashboard/${this.selectedAthlete?.id}`],{
              state: { successMessage: 'Inicial Assessment created successfully!' } 
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
