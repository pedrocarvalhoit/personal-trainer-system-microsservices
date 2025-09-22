import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { WorkoutSessionService } from '../../../core/service/workout-session/workout-session.service';
import { HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-workout-session',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent, FormsModule],
  templateUrl: './create-workout-session.component.html',
  styleUrl: './create-workout-session.component.scss',
})
export class CreateWorkoutSessionComponent implements OnInit{
  athletes: Athlete[] = [];
  selectedAthlete: Athlete | null = null;

  workoutProgramName: string | undefined;
  sessionDate: string | undefined;
  sessionTime: string | undefined;
  clientSubjectiveEffort: number | undefined;
  ptQualityEffortIndicative: number | undefined;
  executed: boolean | undefined;

  constructor(
    private router: Router,
    private workoutServiceService: WorkoutSessionService,
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
   this.saveSession();
  }

  saveSession(): void {
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

      this.workoutServiceService
        .save(
          headers,
          selectedAthleteId,
          this.workoutProgramName || '',
          this.sessionDate || '',
          this.sessionTime || '',
          this.clientSubjectiveEffort || 0,
          this.ptQualityEffortIndicative || 0,
          this.executed
        )
        .subscribe(
          (response) => {
            this.router.navigate(
              [`personaltrainer/athlete-dashboard/${this.selectedAthlete?.id}`],
              {
                state: {
                  successMessage: 'Workout Session created successfully!',
                },
              }
            );
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
