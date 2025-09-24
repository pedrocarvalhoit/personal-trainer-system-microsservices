import { Component, OnInit } from '@angular/core';
import { CooperTestDescription, CooperTestService } from '../../../core/service/cardio-test/cooper-test/cooper-test.service';
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { Athlete, PageResponse, UserService } from '../../../core/service/user/user.service';
import { HttpHeaders } from '@angular/common/http';
import { FooterComponent } from "../../../shared/components/footer/footer.component";
import { NavbarComponent } from "../../../shared/components/navbar/navbar.component";
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-create-cooper-test',
  standalone: true,
  imports: [CommonModule, FooterComponent, NavbarComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './create-cooper-test.component.html',
  styleUrl: './create-cooper-test.component.scss'
})
export class CreateCooperTestComponent implements OnInit{

  athletes: Athlete[] = [];
  selectedAthlete: Athlete | null = null;
  distance: number | undefined;
  athleteAge: number | undefined;
  athleteGender: string | undefined;

  description: string = '';

  constructor(
      private router: Router,
      private cooperTestService: CooperTestService,
      private authService: AuthService,
      private userService: UserService
    ) {}

    ngOnInit(): void {
    this.loadAthletes();
    this.loadDescription();
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
   this.saveTest();
  }

  saveTest(): void {
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

      this.cooperTestService
        .save(
          headers,
          selectedAthleteId,
          this.distance || 0,
          this.athleteAge || 0,
          this.athleteGender || ""
        )
        .subscribe(
          (response) => {
            this.router.navigate([`personaltrainer/athlete-dashboard/${this.selectedAthlete?.id}`],{
              state: { successMessage: 'Cooper Test created successfully!' }
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

  loadDescription(): void {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`,
      });
      this.cooperTestService.getTestDescription(headers).subscribe(
        (response: CooperTestDescription) => {
          this.description = response.description;
          console.log(response.description);
        },
        (error) => {
          console.error('Failed to load description:', error);
        }
      );
    }
  }

}
