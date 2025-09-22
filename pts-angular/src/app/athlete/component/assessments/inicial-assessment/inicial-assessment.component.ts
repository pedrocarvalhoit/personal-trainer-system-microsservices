import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { InicialAssessment, InicialAssessmentService } from '../../../../core/service/inicial-assessment/inicial-assessment.service';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { ChartConfiguration } from 'chart.js';

@Component({
  selector: 'app-inicial-assessment',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './inicial-assessment.component.html',
  styleUrls: ['./inicial-assessment.component.scss']
})
export class InicialAssessmentComponent implements OnInit {

  @Input() athleteId!: string;

  assessments: InicialAssessment[] = [];
  selectedAssessment?: InicialAssessment;

  // Frequência cardíaca
  lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };

  // Pressão arterial
  bloodPressureChartData: ChartConfiguration<'line'>['data'] = {
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
    private inicialAssessmentService: InicialAssessmentService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.loadInicialAssessments();
  }

  loadInicialAssessments(): void {
    const token = this.authService.getToken();
    if (token) {
      const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
      this.inicialAssessmentService.getAllForAthlete(headers, this.athleteId).subscribe(
        (assessments) => {
          this.assessments = assessments.sort(
            (a, b) => new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
          );

          if (this.assessments.length > 0) {
            this.selectedAssessment = this.assessments[this.assessments.length - 1];
            this.updateCharts();
          }
        },
        (error) => console.error('Failed to load assessments:', error)
      );
    } else {
      console.error('Token not found');
    }
  }

  selectAssessment(assessment: InicialAssessment): void {
    this.selectedAssessment = assessment;
  }

  deleteAssessment(assessmentId: number){
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.inicialAssessmentService.deleteAssessment(headers, assessmentId).subscribe(
      () => window.location.reload(),
      (error: any) => console.error('Failed to delete assessment:', error)
    );
  }

  private updateCharts(): void {
    const labels = this.assessments.map(a =>
      new Date(a.createdAt).toLocaleDateString()
    );

    // Frequência cardíaca
    const hrData = this.assessments.map(a => a.restingHeartRate);

    this.lineChartData = {
      labels,
      datasets: [
        {
          data: hrData,
          label: 'Resting Heart Rate (bpm)',
          fill: false,
          tension: 0.3,
          borderColor: '#0d1b2a',
          backgroundColor: '#34669bff',
          pointBackgroundColor: '#34669bff',
          pointBorderColor: '#0d1b2a'
        },
        {
          data: Array(labels.length).fill(60),
          label: 'Min Healthy (60 bpm)',
          borderColor: '#28a745',
          borderDash: [5, 5],
          fill: false,
          pointRadius: 0
        },
        {
          data: Array(labels.length).fill(100),
          label: 'Max Healthy (100 bpm)',
          borderColor: '#dc3545',
          borderDash: [5, 5],
          fill: false,
          pointRadius: 0
        }
      ]
    };

    // Pressão arterial
    const systolicData = this.assessments.map(a => a.systolicBloodPressure);
    const diastolicData = this.assessments.map(a => a.diastolicBloodPressure);

    this.bloodPressureChartData = {
      labels,
      datasets: [
        {
          data: systolicData,
          label: 'Systolic (mmHg)',
          fill: false,
          tension: 0.3,
          borderColor: '#1d3557',
          backgroundColor: '#457b9d',
          pointBackgroundColor: '#457b9d',
          pointBorderColor: '#1d3557'
        },
        {
          data: diastolicData,
          label: 'Diastolic (mmHg)',
          fill: false,
          tension: 0.3,
          borderColor: '#e63946',
          backgroundColor: '#f76874ff',
          pointBackgroundColor: '#e63946',
          pointBorderColor: '#e63946'
        },
        {
          data: Array(labels.length).fill(120),
          label: 'Normal Systolic (120)',
          borderColor: '#28a745',
          borderDash: [5, 5],
          fill: false,
          pointRadius: 0
        },
        {
          data: Array(labels.length).fill(80),
          label: 'Normal Diastolic (80)',
          borderColor: '#17a2b8',
          borderDash: [5, 5],
          fill: false,
          pointRadius: 0
        }
      ]
    };
  }
}
