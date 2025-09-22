import { Component, Input, OnInit } from '@angular/core';
import { PerimetryAssessment, PerimetryAssessmentService } from '../../../../core/service/perimetry-assessment/perimetry-assessment.service';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { HttpHeaders } from '@angular/common/http';
import { ChartConfiguration } from 'chart.js';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';

@Component({
  selector: 'app-perimetry-assessment',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './perimetry-assessment.component.html',
  styleUrl: './perimetry-assessment.component.scss'
})
export class PerimetryAssessmentComponent implements OnInit {

  @Input() athleteId!: string;

  assessments: PerimetryAssessment[] = [];
  selectedAssessment?: PerimetryAssessment;

  // Hip
  lineChartData: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
  };
  
  // Abdominal and Waist
  waistAndAbdominalChartData: ChartConfiguration<'line'>['data'] = {
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
      private perimetryAssessmentService: PerimetryAssessmentService,
      private authService: AuthService
    ) { }

  ngOnInit(): void {
    this.loadPerimetryAssessments();
  }

  loadPerimetryAssessments(): void {
      const token = this.authService.getToken();
      if (token) {
        const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
        this.perimetryAssessmentService.getAllForAthlete(headers, this.athleteId).subscribe(
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
  
  selectAssessment(assessment: PerimetryAssessment): void {
    this.selectedAssessment = assessment;
  }  

  deleteAssessment(assessmentId: number){
    const token = this.authService.getToken();
    if (!token) return;

    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    this.perimetryAssessmentService.deleteAssessment(headers, assessmentId).subscribe(
      () => window.location.reload(),
      (error: any) => console.error('Failed to delete assessment:', error)
    );
  }

  private updateCharts(): void {
    const labels = this.assessments.map(a =>
      new Date(a.createdAt).toLocaleDateString()
    );

    // Hip
    const hrData = this.assessments.map(a => a.hip);

    this.lineChartData = {
      labels,
      datasets: [
        {
          data: hrData,
          label: 'Hip (cm)',
          fill: false,
          tension: 0.3,
          borderColor: '#0d1b2a',
          backgroundColor: '#ffc107',
          pointBackgroundColor: '#ffc107',
          pointBorderColor: '#0d1b2a'
        },
      ]
    };

    // Waist and Abdominal
    const waist = this.assessments.map(a => a.waist);
    const abdomen = this.assessments.map(a => a.abdomen);

    this.waistAndAbdominalChartData = {
      labels,
      datasets: [
        {
          data: waist,
          label: 'Waist (cm)',
          fill: false,
          tension: 0.3,
          borderColor: '#1d3557',
          backgroundColor: '#457b9d',
          pointBackgroundColor: '#457b9d',
          pointBorderColor: '#1d3557'
        },
        {
          data: abdomen,
          label: 'Abdomen (cm)',
          fill: false,
          tension: 0.3,
          borderColor: '#e63946',
          backgroundColor: '#f1faee',
          pointBackgroundColor: '#e63946',
          pointBorderColor: '#e63946'
        }
      ]
    };
  }
}
