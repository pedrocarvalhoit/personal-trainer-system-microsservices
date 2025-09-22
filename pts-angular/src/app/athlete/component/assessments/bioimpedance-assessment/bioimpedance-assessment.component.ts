import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { NgChartsModule } from 'ng2-charts';
import { BioimpedanceAssessment, BioimpedanceAssessmentService } from '../../../../core/service/bioimpedance-assessment/bioimpedance-assessment.service';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import { HttpHeaders } from '@angular/common/http';
import { ChartConfiguration } from 'chart.js';

@Component({
  selector: 'app-bioimpedance-assessment',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './bioimpedance-assessment.component.html',
  styleUrl: './bioimpedance-assessment.component.scss'
})
export class BioimpedanceAssessmentComponent implements OnInit{

  @Input() athleteId!: string;

  assessments: BioimpedanceAssessment[] = [];
  selectedAssessment?: BioimpedanceAssessment;

  // Body Weight and Ideal Body Weight
  bodyWeight: ChartConfiguration<'line'>['data'] = {
    labels: [],
    datasets: []
    };

  // Body Fat Percentual and Body Mass Percentual
  bodyFatPercentual: ChartConfiguration<'line'>['data'] = {
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
        private bioimpedanceAssessmentService: BioimpedanceAssessmentService,
        private authService: AuthService
      ) { }

  ngOnInit(): void {
    this.loadBioimpedanceAssessments();
  }
  
  loadBioimpedanceAssessments(): void {
        const token = this.authService.getToken();
        if (token) {
          const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
          this.bioimpedanceAssessmentService.getAllForAthlete(headers, this.athleteId).subscribe(
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
    
    selectAssessment(assessment: BioimpedanceAssessment): void {
      this.selectedAssessment = assessment;
    }  
  
    deleteAssessment(assessmentId: number){
      const token = this.authService.getToken();
      if (!token) return;
  
      const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
      this.bioimpedanceAssessmentService.deleteAssessment(headers, assessmentId).subscribe(
        () => window.location.reload(),
        (error: any) => console.error('Failed to delete assessment:', error)
      );
    }

  private updateCharts(): void {
    const labels = this.assessments.map(a =>
      new Date(a.createdAt).toLocaleDateString()
    );

    // Body Weight and Ideal Body Weight
    const bodyWheight = this.assessments.map(a => a.bodyWeight);
    const idealWheight = this.assessments.map(a => a.idealWeight);

    this.bodyWeight = {
      labels,
      datasets: [
        {
          data: bodyWheight,
          label: 'Body Weight (kg)',
          fill: false,
          tension: 0.3,
          borderColor: '#0d1b2a',
          backgroundColor: '#ffc107',
          pointBackgroundColor: '#ffc107',
          pointBorderColor: '#0d1b2a'
        },
        {
          data: idealWheight,
          label: 'Ideal Weight (kg)',
          borderColor: '#28a745',
          borderDash: [5, 5],
          fill: false,
          pointRadius: 0
        }
      ]
    };

    // Body Fat Percentual
    const bodyFatPercentual = this.assessments.map(a => a.bodyFatPercentual);
    const idealBodyFatPercentual = this.assessments.map(a => a.idealBodyFatPercentual);

    this.bodyFatPercentual = {
      labels,
      datasets: [
        {
          data: bodyFatPercentual,
          label: 'Body Fat Percentual (%)',
          fill: false,
          tension: 0.3,
          borderColor: '#1d3557',
          backgroundColor: '#457b9d',
          pointBackgroundColor: '#457b9d',
          pointBorderColor: '#1d3557'
        },
        {
          data: idealBodyFatPercentual,
          label: 'Ideal Body Fat Percentual (%)',
          borderColor: '#28a745',
          borderDash: [5, 5],
          fill: false,
          pointRadius: 0
        }
      ]
    };
  }  
}
