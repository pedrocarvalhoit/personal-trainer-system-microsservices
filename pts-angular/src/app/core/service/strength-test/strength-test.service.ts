import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { Observable } from 'rxjs';

export interface StrengthTest{
  id: number;
  createdAt: string;
  maxLoad: number;
  max1Rm: number;
  exercise: string;
}

export interface ExercisesResultsResponseDto{
  month: string;
  maxLoadAvarage: number;
  maxLoad: number;
  max1Rm: number;
  exercise: string;
}

export interface StrengthTestDescription {
    description: string;
}

@Injectable({
  providedIn: 'root'
})
export class StrengthTestService {

  private baseUrl = 'http://localhost:9000/api/v1/strength-test';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) {}

  save(headers: HttpHeaders, selectedAthleteId: string, maxLoad: number, exercise: string) {
    const body = {
      maxLoad,
      exercise
    };
  return this.http.post<any>(`${this.baseUrl}/create-for-athlete/${selectedAthleteId}`, body , { headers });
}

  getAllForAthlete(headers: HttpHeaders, athleteId: string): Observable<StrengthTest[]> {
        return this.http.get<StrengthTest[]>(`${this.baseUrl}/all-results-for-athlete/${athleteId}`, { headers });
      }

  getStatsForChart(headers: HttpHeaders, athleteId: string): Observable<{ [exercise: string]: ExercisesResultsResponseDto[] }> {
  return this.http.get<{ [exercise: string]: ExercisesResultsResponseDto[] }>(
    `${this.baseUrl}/exercises-result-for-chart/${athleteId}`,
    { headers }
  );
}


  deleteTest(headers: HttpHeaders, testId: number): Observable<number>{
      return this.http.delete<number>(`${this.baseUrl}/${testId}`, { headers });
    }

  getTestDescription(headers: HttpHeaders): Observable<StrengthTestDescription> {
      return this.http.get<StrengthTestDescription>(`${this.baseUrl}/description`, { headers });
    }
}
