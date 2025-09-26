import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

import { NgbDropdownModule, NgbNavModule } from '@ng-bootstrap/ng-bootstrap';

import { UserService, Athlete } from '../../../core/service/user/user.service';
import { AuthService } from '../../../auth/services/auth/auth.service';

import { NavbarComponent } from '../../../shared/components/navbar/navbar.component';
import { FooterComponent } from '../../../shared/components/footer/footer.component';
import { WorkoutSessionStatsComponent } from '../../component/training-sessions-and-programs/workout-session-stats/workout-session-stats.component';
import { WorkoutProgramListComponent } from '../../component/training-sessions-and-programs/workout-program-list/workout-program-list.component';
import { CooperTestResultComponent } from '../../component/physical-tests/cooper-test-result/cooper-test-result.component';
import { BackSquatComponent } from '../../component/strength-exercises-tests/back-squat/back-squat.component';
import { DeadliftComponent } from '../../component/strength-exercises-tests/deadlift/deadlift.component';
import { SeatedLowRowComponent } from '../../component/strength-exercises-tests/seated-low-row/seated-low-row.component';
import { BenchPressComponent } from '../../component/strength-exercises-tests/bench-press/bench-press.component';
import { CommonModule } from '@angular/common';
import { InicialAssessmentComponent } from "../../component/assessments/inicial-assessment/inicial-assessment.component";
import { PerimetryAssessmentComponent } from "../../component/assessments/perimetry-assessment/perimetry-assessment.component";
import { BioimpedanceAssessmentComponent } from "../../component/assessments/bioimpedance-assessment/bioimpedance-assessment.component";
import { BioimpedanceRecommendationComponent } from "../../component/bioimpedance-recommendation/bioimpedance-recommendation.component";
import { AthleteReportComponent } from "../../component/athlete-report/athlete-report.component";
import { WorkoutSessionListComponent } from "../../component/training-sessions-and-programs/workout-session-list/workout-session-list.component";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StrengthTestResultComponent } from "../../component/strength-exercises-tests/strength-test-result/strength-test-result.component";

@Component({
  selector: 'app-athlete-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule,
    NgbNavModule,
    NgbDropdownModule,
    NavbarComponent,
    CommonModule,
    FooterComponent,
    WorkoutSessionStatsComponent,
    WorkoutProgramListComponent,
    CooperTestResultComponent,
    BackSquatComponent,
    DeadliftComponent,
    SeatedLowRowComponent,
    BenchPressComponent,
    InicialAssessmentComponent,
    PerimetryAssessmentComponent,
    BioimpedanceAssessmentComponent,
    BioimpedanceRecommendationComponent,
    AthleteReportComponent,
    WorkoutSessionListComponent, StrengthTestResultComponent],
  templateUrl: './athlete-dashboard.component.html',
  styleUrls: ['./athlete-dashboard.component.scss']
})
export class AthleteDashboardComponent implements OnInit {
  athleteId!: string;
  athlete!: Athlete;

  activeTab = 1; // aba inicial
  isAssessmentOpen = false;

  successMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.athleteId = params['athleteId'];
      if (this.athleteId) {
        this.loadAthleteDetails();
        if (history.state && history.state.successMessage) {
          this.showToast(history.state.successMessage);
        }
      } else {
        console.error('Invalid athlete ID');
      }
    });
  }

  showToast(message: string) {
    this.successMessage = message;
    setTimeout(() => this.successMessage = '', 3000);
  }

  toggleAssessments() {
    this.isAssessmentOpen = !this.isAssessmentOpen;
  }

  loadAthleteDetails(): void {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
      this.userService.getAthleteById(headers, this.athleteId).subscribe(
        (athlete: Athlete) => this.athlete = athlete,
        error => console.error('Failed to load athlete details:', error)
      );
    }
  }
}
