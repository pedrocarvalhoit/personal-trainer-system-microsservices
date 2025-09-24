import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../../auth/services/error/error-handler.service';
import { catchError, Observable, tap, throwError } from 'rxjs';

export interface CooperTestDescription {
    description: string;
}

export interface CooperTestResultResponse {
    id: number;
    vo2Max: number;
    athleteAge: number;
    athleteGender: string;
    distance: number;
    classification: string;
}

export interface CooperTestHistoricResponse {
    month: string,
    result: number;
  }

@Injectable({
  providedIn: 'root'
})
export class CooperTestService {

  private baseUrl = 'http://localhost:9000/api/v1/cardio-test/cooper-test';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) { }

  save(headers: HttpHeaders, selectedAthleteId: string, distance: number, athleteAge: number, athleteGender: string) {
      const body = {
        distance,
        athleteAge,
        athleteGender
      };
    return this.http.post<any>(`${this.baseUrl}/create-for-athlete/${selectedAthleteId}`, body , { headers });
  }

   getTestDescription(headers: HttpHeaders): Observable<CooperTestDescription> {
    return this.http.get<CooperTestDescription>(`${this.baseUrl}/description`, { headers });
  }

  getLastResultByAthlete(headers: HttpHeaders, athleteId: string,): Observable<CooperTestResultResponse> {
    return this.http.get<CooperTestResultResponse>(`${this.baseUrl}/last-result-for-athlete/${athleteId}`, { headers });
  }

    getCooperTestHistoric(headers: HttpHeaders, athleteId: string): Observable<CooperTestHistoricResponse[]> {
    return this.http.get<CooperTestHistoricResponse[]>(`${this.baseUrl}/twelve-months-history/${athleteId}`, { headers })
      .pipe(
        tap(response => console.log('Raw API Response:', response)),
        catchError(error => {
          console.error('Error fetching sessions quality:', error);
          return throwError(error);
        })
      );
  }

  deleteTest(headers: HttpHeaders, testId: number): Observable<number>{
    return this.http.delete<number>(`${this.baseUrl}/${testId}`, { headers });
  }

}
