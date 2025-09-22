import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { WorkoutSessionService, WorkoutSessionQualityResponseDto } from '../../../../core/service/workout-session/workout-session.service';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';

@Component({
  selector: 'app-workout-session-stats',
  standalone: true,
  imports: [NgChartsModule],
  templateUrl: './workout-session-stats.component.html',
  styleUrls: ['./workout-session-stats.component.scss']
})
export class WorkoutSessionStatsComponent implements OnInit {

  @Input() athleteId!: string;

  // Monthly Stats
  totalSessionsActualMonth = 0;
  totalExecutedSessionsActualMonth = 0;
  totalNotExecutedSessionsActualMonth = 0;
  percentActualMonthExecuted = 0;
  percentActualMonthNotExecuted = 0;

  // All Time Stats
  totalAllTimeSessions = 0;
  totalAllTimeExecutedSessions = 0;
  totalAllTimeNotExecutedSessions = 0;
  percentAllTimeExecuted = 0;
  percentAllTimeNotExecuted = 0;

  // Chart data
  data: ChartConfiguration<'line'>['data'] = { labels: [], datasets: [] };
  options: ChartConfiguration<'line'>['options'] = {
    responsive: true,
    plugins: {
      legend: { position: 'top' },
      tooltip: {
        callbacks: {
          label: (ctx: any) => `${ctx.dataset.label}: ${ctx.raw.toFixed(1)}`
        }
      }
    },
    scales: {
      y: { beginAtZero: true, max: 10 }
    }
  };

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private workoutSessionService: WorkoutSessionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadMonthlyStats();
    this.loadAllTimeStats();
    this.loadTrainingStats();
  }

  private getAuthHeaders(): HttpHeaders | null {
    const token = this.authService.getToken();
    return token ? new HttpHeaders({ Authorization: `Bearer ${token}` }) : null;
  }

  private loadMonthlyStats() {
    const headers = this.getAuthHeaders();
    if (!headers) return;

    this.workoutSessionService.getMonthlyStatsSummary(headers, this.athleteId).subscribe({
      next: summary => {
        this.totalSessionsActualMonth = summary.totalSessionsPlanedActualMonth;
        this.totalExecutedSessionsActualMonth = summary.totalExecutedSessionsActualMonth;
        this.totalNotExecutedSessionsActualMonth = summary.totalNotExecutedSessionsActualMonth;
        this.percentActualMonthExecuted = summary.percentExecuted;
        this.percentActualMonthNotExecuted = summary.percentNotExecuted;
      },
      error: err => console.error('Failed to fetch monthly stats', err)
    });
  }

  private loadAllTimeStats() {
    const headers = this.getAuthHeaders();
    if (!headers) return;

    this.workoutSessionService.getAllTimeStatsSummary(headers, this.athleteId).subscribe({
      next: summary => {
        this.totalAllTimeSessions = summary.totalSessionsPlanedAlltime;
        this.totalAllTimeExecutedSessions = summary.totalExecutedSessionsAlltime;
        this.totalAllTimeNotExecutedSessions = summary.totalNotExecutedSessionsAlltime;
        this.percentAllTimeExecuted = summary.percentExecuted;
        this.percentAllTimeNotExecuted = summary.percentNotExecuted;
      },
      error: err => console.error('Failed to fetch all time stats', err)
    });
  }

  private loadTrainingStats() {
    const headers = this.getAuthHeaders();
    if (!headers) return;

    this.workoutSessionService.getSessionsQuality(headers, this.athleteId).subscribe({
      next: (response: WorkoutSessionQualityResponseDto[]) => {
        if (!Array.isArray(response)) return console.error('Response is not an array', response);

        const labels: string[] = [];
        const subjectiveEffortData: number[] = [];
        const qualityEffortData: number[] = [];

        response.forEach(stat => {
          labels.push(stat.month);
          subjectiveEffortData.push(stat.clientSubjectEffortAvarage);
          qualityEffortData.push(stat.ptQualityEffortAvarage);
        });

        this.data = {
          labels,
          datasets: [
            {
              label: 'Subjective Effort Scale',
              data: subjectiveEffortData,
              fill: false,
              tension: 0.3,
              borderColor: '#0d1b2a',
              backgroundColor: '#34669bff',
              pointBackgroundColor: '#34669bff',
              pointBorderColor: '#0d1b2a'
            },
            {
              label: 'Training Session Effort Quality',
              data: qualityEffortData,
              fill: false,
              borderColor: '#ffc107',
              backgroundColor: '#ffdf80ff',
              pointBackgroundColor: '#ffdf80ff',
              pointBorderColor: '#ffc107'
            }
          ]
        };
      },
      error: err => console.error('Failed to fetch training stats', err)
    });
  }

}
