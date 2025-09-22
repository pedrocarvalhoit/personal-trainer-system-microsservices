
import { HttpHeaders } from '@angular/common/http';
import { ChartConfiguration } from 'chart.js';
import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { NgChartsModule } from 'ng2-charts';
import { BioimpedanceRecommendation, BioimpedanceRecommendationService } from '../../../core/service/bioimpedance-recommendation/bioimpedance-recommendation.service';
import { AuthService } from '../../../auth/services/auth/auth.service';

@Component({
  selector: 'app-bioimpedance-recommendation',
  standalone: true,
  imports: [CommonModule, NgChartsModule],
  templateUrl: './bioimpedance-recommendation.component.html',
  styleUrl: './bioimpedance-recommendation.component.scss'
})
export class BioimpedanceRecommendationComponent implements OnInit{

  @Input() athleteId!: string;

  recommendation: BioimpedanceRecommendation | undefined;

  constructor(
          private bioimpedanceRecommendationService: BioimpedanceRecommendationService,
          private authService: AuthService
        ) { }

  ngOnInit(): void {
    this.loadBioimpedanceRecommendations();
  }

  loadBioimpedanceRecommendations(): void {
        const token = this.authService.getToken();
        if (token) {
          const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
          this.bioimpedanceRecommendationService.getLastForAthlete(headers, this.athleteId).subscribe(
            recommendation => {
              this.recommendation = recommendation;
            },
            (error) => console.error('Failed to load recommendation:', error)
          );
        } else {
          console.error('Token not found');
        }
      }
          

}
