import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { Observable } from 'rxjs';

export interface BioimpedanceRecommendation{
  id: number;
  createdAt: string;
  watterIntake: number;
  caloriesIntakeToAchieveIdealWeightSlow: number;
  caloriesIntakeToAchieveIdealWeightModerate: number;
  caloriesIntakeToAchieveIdealWeightFast: number;
  daysToAchieveIdealWeightSlow: number;
  daysToAchieveIdealWeightModerate: number;
  daysToAchieveIdealWeightFast: number;
}

@Injectable({
  providedIn: 'root'
})
export class BioimpedanceRecommendationService {

  private baseUrl = 'http://localhost:9000/api/v1/bioimpedance-recommendation';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) { }

  getLastForAthlete(headers: HttpHeaders, athleteId: string): Observable<BioimpedanceRecommendation> {
          return this.http.get<BioimpedanceRecommendation>(`${this.baseUrl}/last-for-athlete/${athleteId}`, { headers });
        }
}
