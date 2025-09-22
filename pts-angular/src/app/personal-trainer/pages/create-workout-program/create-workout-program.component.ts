import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from '../../../shared/components/footer/footer.component';
import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { WorkoutSessionService } from '../../../core/service/workout-session/workout-session.service';
import { HttpHeaders } from '@angular/common/http';
import { WorkoutProgramService } from '../../../core/service/workout-program/workout-program.service';
import { EditorModule } from '@tinymce/tinymce-angular';

@Component({
  selector: 'app-create-workout-program',
  standalone: true,
  imports: [CommonModule, NavbarComponent, FooterComponent, FormsModule, EditorModule],
  templateUrl: './create-workout-program.component.html',
  styleUrl: './create-workout-program.component.scss'
})
export class CreateWorkoutProgramComponent implements OnInit{

  athletes: Athlete[] = [];
  selectedAthlete: Athlete | null = null;

  title: string | undefined;
  startDate: string | undefined;
  endDate: string | undefined;
  trainingSessionContent: string | undefined;
  note: string | undefined;
  enabled: boolean | undefined;

    tinyMceConfig: any = {
    height: 300,
    menubar: false,
    plugins: [
      'advlist autolink lists link image charmap print preview anchor',
      'searchreplace visualblocks code fullscreen',
      'insertdatetime media table paste code help wordcount',
    ],
    toolbar:
      'undo redo | formatselect | bold italic backcolor | \
      alignleft aligncenter alignright alignjustify | \
      bullist numlist outdent indent | removeformat | help',
  };

    constructor(
      private router: Router,
      private workoutProgramService: WorkoutProgramService,
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
   this.saveProgram();
  }

  saveProgram(): void {
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

      this.workoutProgramService
        .save(
          headers,
          selectedAthleteId,
          this.title || '',
          this.startDate || '',
          this.endDate || '',
          this.trainingSessionContent || '',
          this.note || '',
          this.enabled
        )
        .subscribe(
          (response) => {
            this.router.navigate(
              [`personaltrainer/athlete-dashboard/${this.selectedAthlete?.id}`],
              {
                state: {
                  successMessage: 'Workout Program created successfully!',
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
