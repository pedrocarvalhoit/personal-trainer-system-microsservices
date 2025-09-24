import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { NgChartsModule } from 'ng2-charts';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { CooperTestHistoricResponse, CooperTestResultResponse, CooperTestService } from '../../../../core/service/cardio-test/cooper-test/cooper-test.service';
import { HttpHeaders } from '@angular/common/http';
import { ChartConfiguration } from 'chart.js';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-cooper-test-result',
  standalone: true,
  imports: [CommonModule, NgChartsModule, FormsModule, ReactiveFormsModule],
  templateUrl: './cooper-test-result.component.html',
  styleUrl: './cooper-test-result.component.scss'
})
export class CooperTestResultComponent implements OnInit {

  @Input() athleteId!: string;

  result : number | undefined;
  testId: number | undefined;
  athleteAge: number | undefined;
  athleteGender: string | undefined;
  distance: number | undefined;
  classification: string | undefined;
  results: CooperTestHistoricResponse[] = [];

  classificationClass: string | undefined;

  // Result Historic
    hitoricChart: ChartConfiguration<'line'>['data'] = {
      labels: [],
      datasets: []
    };

    lineChartOptions: ChartConfiguration<'line'>['options'] = {
      responsive: true,
      plugins: {
        legend: {
          labels: { color: '#0d1b2a' }
        }
      },
      scales: {
        x: { ticks: { color: '#0d1b2a' } },
        y: { ticks: { color: '#0d1b2a' }, beginAtZero: false }
      }
    };

  constructor(
    private cooperTestService: CooperTestService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loadCooperTestResult();
    this.loadCooperHistoric();
  }

  loadCooperTestResult() {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({
        Authorization: `Bearer ${token}`,
      });
      this.cooperTestService.getLastResultByAthlete(headers, this.athleteId).subscribe(
        (response: CooperTestResultResponse) => {
          this.result = response.vo2Max;
          this.testId = response.id
          this.athleteAge = response.athleteAge;
          this.athleteGender = response.athleteGender;
          this.distance = response.distance
          this.classification = response.classification;
          this.setClassificationClass(this.classification);
        },
        (error) => {
          console.error('Failed to load enabled clients:', error);
        }
      );
    }
  }

loadCooperHistoric(): void {
  const token = this.authService.getToken();
  if (token) {
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.cooperTestService.getCooperTestHistoric(headers, this.athleteId).subscribe({
      next: (results) => {
        this.results = results;

        this.hitoricChart = {
          labels: results.map(r => r.month), // direto do DTO
          datasets: [
            {
              data: results.map(r => r.result),
              label: 'VO₂ Max',
              fill: false,
              borderColor: '#1e3c72',
              backgroundColor: '#1e3c72',
              tension: 0.3,
              pointRadius: 5,
              pointBackgroundColor: '#1e3c72'
            }
          ]
        };
      },
      error: (err) => {
        console.error('Failed to load assessments:', err);
      }
    });
  } else {
    console.error('Token not found');
  }
}

setClassificationClass(classification: string): void {
    switch (classification.toLowerCase()) {
      case 'very_weak':
        this.classificationClass = 'very-weak';
        break;
      case 'weak':
        this.classificationClass = 'weak';
        break;
      case 'regular':
        this.classificationClass = 'regular';
        break;
      case 'good':
        this.classificationClass = 'good';
        break;
      case 'excellent':
        this.classificationClass = 'excellent';
        break;
      default:
        this.classificationClass = 'undefined';
        break;
    }
  }

  deleteThisTest(testId: number) {
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.cooperTestService.deleteTest(headers, testId).subscribe(
      () => window.location.reload(),
      (error: any) => console.error('Failed to delete test:', error)
    );
}

  navigateTo(route: string) {
    this.router.navigate([route]);
  }

}
