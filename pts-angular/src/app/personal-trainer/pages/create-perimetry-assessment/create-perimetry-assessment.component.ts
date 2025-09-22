import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';
import { FormsModule } from '@angular/forms';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { Router } from '@angular/router';
import { PerimetryAssessment, PerimetryAssessmentService } from '../../../core/service/perimetry-assessment/perimetry-assessment.service';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-create-perimetry-assessment',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './create-perimetry-assessment.component.html',
  styleUrl: './create-perimetry-assessment.component.scss'
})
export class CreatePerimetryAssessmentComponent {

  athletes: Athlete[] = [];
  selectedAthlete: Athlete | null = null;
  shoulder: number | undefined;
  torax: number | undefined;
  waist: number | undefined;
  abdomen: number | undefined;
  hip: number | undefined;
  rightArm: number | undefined;
  leftArm: number | undefined;
  rightThigh: number | undefined;
  athleteAge: number | undefined;
  leftThigh: number | undefined;
  rightLeg: number | undefined;
  leftLeg: number | undefined;

  constructor(
      private router: Router,
      private perimetryAssessmentService: PerimetryAssessmentService,
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

      this.perimetryAssessmentService
        .save(
          headers,
          selectedAthleteId,
          this.shoulder || 0,
          this.torax || 0,
          this.waist || 0,
          this.abdomen || 0,
          this.hip || 0,
          this.rightArm || 0,
          this.leftArm || 0,
          this.rightThigh || 0,
          this.leftThigh || 0,
          this.rightLeg || 0,
          this.leftLeg || 0
        )
        .subscribe(
          (response) => {
            this.router.navigate([`personaltrainer/athlete-dashboard/${this.selectedAthlete?.id}`],{
              state: { successMessage: 'Perimetry Assessment created successfully!' } 
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
