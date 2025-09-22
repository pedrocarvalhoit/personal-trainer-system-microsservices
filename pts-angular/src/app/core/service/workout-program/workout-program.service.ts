import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { catchError, Observable } from 'rxjs';

export interface WorkoutProgram{
  id: number;
  title: string;
  startDate: string;
  endDate: string;
  trainingSessionContent: string;
  note: string;
  enabled: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class WorkoutProgramService {

  private baseUrl = 'http://localhost:9000/api/v1/workout-program';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) { }

  save(headers: HttpHeaders, selectedAthleteId: string, title: string, startDate: string, endDate: string, trainingSessionContent: string, note: string,
    enabled: boolean | undefined) {
      const body = {
        title,
        startDate,
        endDate,
        trainingSessionContent,
        note,
        enabled
      };
    return this.http.post<any>(`${this.baseUrl}/create-for-athlete/${selectedAthleteId}`, body , { headers });
  }

  getAllForAthlete(headers: HttpHeaders, athleteId: string): Observable<WorkoutProgram[]> {
        return this.http.get<WorkoutProgram[]>(`${this.baseUrl}/all-for-athlete/${athleteId}`, { headers });
      }

  updateProgram(
    headers: HttpHeaders,
    programId: number,
    sessionData: Partial<WorkoutProgram>
  ) {
    return this.http.patch<WorkoutProgram>(
      `${this.baseUrl}/update/${programId}`,
      sessionData,
      { headers }
    );
  }

   exportToPdf(headers: HttpHeaders, programId: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/export-pdf/${programId}`, { headers, responseType: 'blob'}).pipe(
      catchError((error: any) => {
        console.error('Download PDF failed', error);
        throw error;
      })
    );
  }
}
