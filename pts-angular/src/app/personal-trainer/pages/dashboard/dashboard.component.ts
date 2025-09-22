import { Component, OnInit } from '@angular/core';
import { FooterComponent } from "../../../shared/components/footer/footer.component";
import { NavbarComponent } from "../../../shared/components/navbar/navbar.component";
import { Router } from '@angular/router';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { WorkoutSessionService } from '../../../core/service/workout-session/workout-session.service';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FooterComponent, NavbarComponent],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  successMessage: string = '';

  workoutStats: any = {
    totalSessionsPerMonth: 0,
    totalExecutedSessionsPerMonth: 0,
    totalNotExecutedSessionsPerMonth: 0,
    firstPlaceAthleteName: '',
    secondPlaceAthleteName: '',
    thirdPlaceAthleteName: '',
    firstPlaceSessions: 0,
    secondPlaceSessions: 0,
    thirdPlaceSessions: 0
  };

  constructor(private router: Router, private authService: AuthService, private workoutSessionService: WorkoutSessionService) {}

 ngOnInit(): void {
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });

    this.workoutSessionService.getTotalMonthlyWorkoutSessions(headers)
      .subscribe(summary => {
        this.workoutStats.totalSessionsPerMonth = summary.totalSessionsPerMonth;
        this.workoutStats.totalExecutedSessionsPerMonth = summary.totalExecutedSessionsPerMonth;
        this.workoutStats.totalNotExecutedSessionsPerMonth = summary.totalNotExecutedSessionsPerMonth;

        this.workoutStats.firstPlaceAthleteName = summary.bestThreeAthletesNames[0] || 'N/A';
        this.workoutStats.secondPlaceAthleteName = summary.bestThreeAthletesNames[1] || 'N/A';
        this.workoutStats.thirdPlaceAthleteName = summary.bestThreeAthletesNames[2] || 'N/A';

        this.workoutStats.firstPlaceSessions = summary.bestThreeAthletesNumOfSessions[0];
        this.workoutStats.secondPlaceSessions = summary.bestThreeAthletesNumOfSessions[1];
        this.workoutStats.thirdPlaceSessions = summary.bestThreeAthletesNumOfSessions[2];
      }, error => {
        console.error('Failed to fetch workout summary', error);
      });
  }

  showToast(message: string) {
    this.successMessage = message;
    setTimeout(() => this.successMessage = '', 2000); // desaparece após 2 segundos
  }

  navigateToRegisterAthlete() {
    const personalTrainerId = this.authService.getLoggedUserId();
    this.router.navigate(['/register'], {
      queryParams: {
        role: 'ATHLETE',
        trainerId: personalTrainerId
      }
    });
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
