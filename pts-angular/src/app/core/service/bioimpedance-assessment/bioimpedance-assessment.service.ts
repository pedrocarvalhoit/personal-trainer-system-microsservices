import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { Observable } from 'rxjs';

export interface BioimpedanceAssessment{
  id: number;
  createdAt: string;
  age: number;
  height: number;
  bodyWeight: number;
  bmi: number;
  bodyFatPercentual: number;
  bodyMassPercentual: number;
  basalMetabolismRateCalories: number;
  visceralFatIndice: number;
  idealWeight: number;
  idealBodyFatPercentual: number;
  notes: string;

}

@Injectable({
  providedIn: 'root'
})
export class BioimpedanceAssessmentService {

  private baseUrl = 'http://localhost:9000/api/v1/bioimpedance-assessment';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) { }

  save(headers: HttpHeaders, selectedAthleteId: string, age: number, height: number, bodyWeight: number, bmi: number, bodyFatPercentual: number, bodyMassPercentual: number,
   basalMetabolismRateCalories: number, visceralFatIndice: number, idealWeight: number, idealBodyFatPercentual: number, notes: string   
  ) {
      const body = {
        age,
        height,
        bodyWeight,
        bmi,
        bodyFatPercentual,
        bodyMassPercentual,
        basalMetabolismRateCalories,
        visceralFatIndice,
        idealWeight,
        idealBodyFatPercentual,
        notes
      };
    return this.http.post<any>(`${this.baseUrl}/create/${selectedAthleteId}`, body , { headers });
  }

  getAllForAthlete(headers: HttpHeaders, athleteId: string): Observable<BioimpedanceAssessment[]> {
        return this.http.get<BioimpedanceAssessment[]>(`${this.baseUrl}/all-for-athlete/${athleteId}`, { headers });
      }
  
  deleteAssessment(headers: HttpHeaders, assessmentId: number): Observable<number>{
      return this.http.delete<number>(`${this.baseUrl}/${assessmentId}`, { headers });
    }      
}
