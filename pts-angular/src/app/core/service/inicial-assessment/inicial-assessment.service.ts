import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { Observable } from 'rxjs';

export interface InicialAssessment{
  id: number;
  createdAt: string;
  sedentary: boolean;
  smoker:boolean;
  systolicBloodPressure: number;
  diastolicBloodPressure: number;
  restingHeartRate: number;

}

@Injectable({
  providedIn: 'root'
})
export class InicialAssessmentService {

  private baseUrl = 'http://localhost:9000/api/v1/inicial-assessment';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) {}

  save(headers: HttpHeaders, selectedAthleteId: string,athleteAge: number, restingHeartRate: number, systolicBloodPressure: number, diastolicBloodPressure: number, sedentary: boolean | undefined, smoker: boolean | undefined) {
    const body = {
      athleteAge,
      restingHeartRate,
      systolicBloodPressure,
      diastolicBloodPressure,
      sedentary,
      smoker
    };
  return this.http.post<any>(`${this.baseUrl}/create/${selectedAthleteId}`, body , { headers });
}

  getAllForAthlete(headers: HttpHeaders, athleteId: string): Observable<InicialAssessment[]> {
      return this.http.get<InicialAssessment[]>(`${this.baseUrl}/all-for-athlete/${athleteId}`, { headers });
    }

  deleteAssessment(headers: HttpHeaders, assessmentId: number): Observable<number>{
    return this.http.delete<number>(`${this.baseUrl}/${assessmentId}`, { headers });
  }  

}
