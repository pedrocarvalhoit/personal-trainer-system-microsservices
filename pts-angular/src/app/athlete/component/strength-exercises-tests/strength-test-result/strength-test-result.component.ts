import 'chart.js/auto';
import { Component, Input, OnInit } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from '../../../../auth/services/auth/auth.service';
import {
  StrengthTest,
  StrengthTestService,
  ExercisesResultsResponseDto,
  StrengthTestDescription
} from './../../../../core/service/strength-test/strength-test.service';
import { CommonModule } from '@angular/common';
import { NgChartsModule } from 'ng2-charts';
import { FormsModule } from '@angular/forms';
import { EditorModule } from '@tinymce/tinymce-angular';
import { ChartConfiguration } from 'chart.js';

@Component({
  selector: 'app-strength-test-result',
  standalone: true,
  imports: [CommonModule, NgChartsModule, FormsModule, EditorModule],
  templateUrl: './strength-test-result.component.html',
  styleUrl: './strength-test-result.component.scss'
})
export class StrengthTestResultComponent implements OnInit {
  @Input() athleteId!: string;

  tests: StrengthTest[] = [];
  charts: { [key: string]: ChartConfiguration<'bar' | 'line'>['data'] } = {};

  selectedExercise = '';
  maxLoad?: number;
  editDialogOpen = false;
  description = '';

  chartOptions: ChartConfiguration['options'] = {
    responsive: true,
    plugins: {
      legend: { labels: { color: '#0d1b2a' } }
    },
    scales: {
      x: {
        ticks: { color: '#0d1b2a' },
        type: 'category'
      },
      y: {
        ticks: { color: '#0d1b2a' },
        beginAtZero: true
      }
    }
  };

  constructor(
    private router: Router,
    private strengthTestService: StrengthTestService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.loadTests();
    this.loadCharts();
    this.loadDescription();
  }

  private getHeaders(): HttpHeaders | null {
    const token = this.authService.getToken();
    return token ? new HttpHeaders({ Authorization: `Bearer ${token}` }) : null;
  }

  loadDescription(): void {
    const headers = this.getHeaders();
    if (!headers) return;
    this.strengthTestService.getTestDescription(headers).subscribe({
      next: (res: StrengthTestDescription) => (this.description = res.description),
      error: err => console.error('Failed to load description:', err)
    });
  }

  loadTests(): void {
    const headers = this.getHeaders();
    if (!headers) return;
    this.strengthTestService.getAllForAthlete(headers, this.athleteId).subscribe({
      next: tests => {
        this.tests = tests.sort(
          (a, b) => new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
      },
      error: err => console.error('Failed to load tests:', err)
    });
  }

  deleteTest(testId: number): void {
    const headers = this.getHeaders();
    if (!headers) return;
    this.strengthTestService.deleteTest(headers, testId).subscribe({
      next: () => this.loadTests(),
      error: err => console.error('Failed to delete test:', err)
    });
  }

  openCreateTestDialog(exercise: string): void {
    this.selectedExercise = exercise;
    this.maxLoad = undefined;
    this.editDialogOpen = true;
  }

  closeEditDialog(): void {
    this.editDialogOpen = false;
  }

  createTest(): void {
    if (!this.maxLoad) return;
    const headers = this.getHeaders();
    if (!headers) return;
    this.strengthTestService.save(headers, this.athleteId, this.maxLoad, this.selectedExercise).subscribe({
      next: () => {
        this.loadTests();
        this.closeEditDialog();
        this.router.navigate([`personaltrainer/athlete-dashboard/${this.athleteId}`], {
          state: { successMessage: 'Strength Test created successfully!' }
        });
      },
      error: err => console.error('Failed to create test:', err)
    });
  }

  private loadCharts(): void {
  const headers = this.getHeaders();
  if (!headers) return;

  this.strengthTestService.getStatsForChart(headers, this.athleteId).subscribe({
    next: (data: { [exercise: string]: ExercisesResultsResponseDto[] }) => {
      for (const exercise of Object.keys(data)) {
        let results = data[exercise] || [];

        // 🟡 Inverte a ordem para que o mais antigo fique primeiro
        results = results.slice().reverse();

        const labels = results.map(r => this.getMonthName(r.month));
        const maxLoadData = results.map(r => r.maxLoad);
        const max1RmData = results.map(r => r.max1Rm);
        const maxLoadAvgData = results.map(r => r.maxLoadAvarage);

        this.charts[exercise] = {
          labels,
          datasets: [
            { type: 'bar', label: 'Max Load', data: maxLoadData, backgroundColor: '#ffe187ff' },
            { type: 'bar', label: 'Max 1RM', data: max1RmData, backgroundColor: '#34669bff' },
            {
              type: 'line',
              label: 'Max Load Average',
              data: maxLoadAvgData,
              borderColor: '#48cf67ff',
              backgroundColor: '#48cf67ff',
              fill: false,
              tension: 0.3,
              pointRadius: 3,
              borderWidth: 2
            }
          ]
        };
      }

      this.charts = { ...this.charts }; // força update visual
    },
    error: err => console.error('Failed to load chart data', err)
  });
}

  private getMonthName(monthString: string): string {
    if (!monthString || monthString.trim() === '') return '';
    const date = new Date(monthString + '-01T00:00:00');
    return isNaN(date.getTime())
      ? monthString
      : date.toLocaleString('default', { month: 'short' });
  }
}
