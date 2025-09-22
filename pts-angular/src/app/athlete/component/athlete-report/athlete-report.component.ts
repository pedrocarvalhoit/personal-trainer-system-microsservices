import { Component, Input, OnInit } from '@angular/core';
import { AthleteReport, AthleteReportService } from '../../../core/service/athlete-report/athlete-report.service';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { HttpHeaders } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-athlete-report',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './athlete-report.component.html',
  styleUrl: './athlete-report.component.scss'
})
export class AthleteReportComponent implements OnInit{

  @Input() athleteId!: string;

  athleteReport: AthleteReport | undefined;

  constructor(
            private athleteReportService: AthleteReportService,
            private authService: AuthService
          ) { }

  ngOnInit(): void {
    this.loadAthleteReports();
  }        

  loadAthleteReports(): void {
          const token = this.authService.getToken();
          if (token) {
            const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
            this.athleteReportService.getLastForAthlete(headers, this.athleteId).subscribe(
              athleteReport => {
                this.athleteReport = athleteReport;
              },
              (error) => console.error('Failed to load report:', error)
            );
          } else {
            console.error('Token not found');
          }
        }
  
  generateReport(): void {
    const token = this.authService.getToken();

    if (this.athleteId && token) {
      const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
      this.athleteReportService.generateForAthlete(headers, this.athleteId).subscribe(
        response => {
          console.log('Report generated successfully:', response);
          this.loadAthleteReports(); 
        },
        error => console.error('Erro ao gerar report:', error)
      );
    } else {
      console.error('athleteId or token not found');
    }
  }

  getBloodPressureClass(status?: string): string {
  switch (status) {
    case 'NORMAL': return 'status-good';
    case 'ELEVATED': return 'status-warning';
    case 'HYPERTENSION_STAGE_1': 
    case 'HYPERTENSION_STAGE_2': return 'status-bad';
    case 'HYPERTENSIVE_CRISIS': return 'status-critical';
    default: return 'status-unknown';
  }
}

getBMIClass(status?: string): string {
  switch (status) {
    case 'SEVERE_THINNESS':
    case 'OBESITY_CLASS_II':
    case 'OBESITY_CLASS_III': return 'status-critical';
    case 'MODERATE_THINNESS':
    case 'OBESITY_CLASS_I': return 'status-bad';
    case 'MILD_THINNESS':
    case 'OVERWEIGHT': return 'status-warning';
    case 'NORMAL': return 'status-good';
    default: return 'status-unknown';
  }
}

getWaistHipClass(status?: string): string {
  switch (status) {
    case 'LOW_RISK': return 'status-good';
    case 'MODERATE_RISK': return 'status-warning';
    case 'HIGH_RISK': return 'status-bad';
    default: return 'status-unknown';
  }
}
      

}
