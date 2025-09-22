import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { catchError, Observable, tap, throwError } from 'rxjs';

export interface WorkoutSession{
  id: number;
  workoutProgramName: string;
  sessionDate: string;
  sessionTime: string;
  clientSubjectiveEffort: number;
  ptQualityEffortIndicative: number;
  executed: boolean;
}

export interface PageResponse<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
  empty: boolean;
  numberOfElements: number;
}

export interface WorkoutSessionAthleteActualMonthSummaryResponseDto {
  totalSessionsPlanedActualMonth: number;
  totalExecutedSessionsActualMonth: number;
  totalNotExecutedSessionsActualMonth: number;
  percentExecuted: number;
  percentNotExecuted: number;
}

export interface WorkoutSessionAthleteAllTimeSummaryResponseDto{
  totalSessionsPlanedAlltime: number;
  totalExecutedSessionsAlltime: number;
  totalNotExecutedSessionsAlltime: number;
  percentExecuted: number;
  percentNotExecuted: number;
}

export interface WorkoutSessionQualityResponseDto {
  month: string;
  clientSubjectEffortAvarage: number;
  ptQualityEffortAvarage: number;
}

interface WorkoutSessionTotalSummaryResponseDto {
  totalSessionsPerMonth: number;
  totalExecutedSessionsPerMonth: number;
  totalNotExecutedSessionsPerMonth: number;
  bestThreeAthletesNames: string[];
  bestThreeAthletesNumOfSessions: number[];
}

@Injectable({
  providedIn: 'root'
})
export class WorkoutSessionService {

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService) { }

  private baseUrl = 'http://localhost:9000/api/v1/workout-session';

  save(headers: HttpHeaders, selectedAthleteId: string, workoutProgramName: string, sessionDate: string,
     sessionTime: string, clientSubjectiveEffort: number, ptQualityEffortIndicative: number, executed: boolean | undefined) {
    const body = {
      workoutProgramName,
      sessionDate,
      sessionTime,
      clientSubjectiveEffort,
      ptQualityEffortIndicative,
      executed
    };
  return this.http.post<any>(`${this.baseUrl}/create/${selectedAthleteId}`, body , { headers });
}

  getAllForAthlete(headers: HttpHeaders, athleteId: string, page: number = 0, size: number = 50): Observable<PageResponse<WorkoutSession>> {
      return this.http.get<PageResponse<WorkoutSession>>(
        `${this.baseUrl}/all-for-athlete/${athleteId}`,
        { headers, params: { page: page.toString(), size: size.toString() } }
      );
    }

  deleteSession(headers: HttpHeaders, sessionId: number): Observable<number>{
    return this.http.delete<number>(`${this.baseUrl}/delete/${sessionId}`, { headers });
  }

  executeSession(headers: HttpHeaders, sessionId: number): Observable<number> {
  return this.http.patch<number>(`${this.baseUrl}/execute/${sessionId}`, null, { headers });
}

  updateSession(
  headers: HttpHeaders,
  sessionId: number,
  sessionData: Partial<WorkoutSession>
) {
  return this.http.patch<WorkoutSession>(
    `${this.baseUrl}/update/${sessionId}`,
    sessionData,
    { headers }
  );
}

//Athlete dashboard
  getMonthlyStatsSummary(headers: HttpHeaders, athleteId: string): Observable<WorkoutSessionAthleteActualMonthSummaryResponseDto> {
    return this.http.get<WorkoutSessionAthleteActualMonthSummaryResponseDto>
    (`${this.baseUrl}/get-workout-stats-actual-month/${athleteId}`, { headers });
  }

  getAllTimeStatsSummary(headers: HttpHeaders, athleteId: string): Observable<WorkoutSessionAthleteAllTimeSummaryResponseDto> {
    return this.http.get<WorkoutSessionAthleteAllTimeSummaryResponseDto>
    (`${this.baseUrl}/get-workout-stats-all-time/${athleteId}`, { headers });
  }

    getSessionsQuality(headers: HttpHeaders, athleteId: string): Observable<WorkoutSessionQualityResponseDto[]> {
    return this.http.get<WorkoutSessionQualityResponseDto[]>(`${this.baseUrl}/get-workout-quality-stats-for-chart/${athleteId}`, { headers })
      .pipe(
        tap(response => console.log('Raw API Response:', response)),
        catchError(error => {
          console.error('Error fetching sessions quality:', error);
          return throwError(error);
        })
      );
  }

  //Home page
  getTotalMonthlyWorkoutSessions(headers: HttpHeaders): Observable<WorkoutSessionTotalSummaryResponseDto> {
    return this.http.get<WorkoutSessionTotalSummaryResponseDto>(`${this.baseUrl}/get-workout-summary`, { headers });
  }

}



