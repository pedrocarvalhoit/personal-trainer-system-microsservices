import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Observable } from 'rxjs';

export interface PerimetryAssessment{
  id: number;
  createdAt: string;
  shoulder: number;
  torax: number;
  waist:number;
  abdomen: number;
  hip: number;
  rightArm: number;
  leftArm: number;
  rightThigh: number;
  leftThigh: number;
  rightLeg: number;
  leftLeg: number;
}

@Injectable({
  providedIn: 'root'
})
export class PerimetryAssessmentService {

  private baseUrl = 'http://localhost:9000/api/v1/perimetry-assessment';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService) { }

  save(headers: HttpHeaders, selectedAthleteId: string, shoulder: number, torax: number, waist: number, abdomen: number, hip: number,
    rightArm: number, leftArm: number, rightThigh: number, leftThigh: number, rightLeg: number, leftLeg: number
  ) {
      const body = {
        shoulder,
        torax,
        waist,
        abdomen,
        hip,
        rightArm,
        leftArm,
        rightThigh,
        leftThigh,
        rightLeg,
        leftLeg
      };
    return this.http.post<any>(`${this.baseUrl}/create/${selectedAthleteId}`, body , { headers });
  }

  getAllForAthlete(headers: HttpHeaders, athleteId: string): Observable<PerimetryAssessment[]> {
        return this.http.get<PerimetryAssessment[]>(`${this.baseUrl}/all-for-athlete/${athleteId}`, { headers });
      }

  deleteAssessment(headers: HttpHeaders, assessmentId: number): Observable<number>{
      return this.http.delete<number>(`${this.baseUrl}/${assessmentId}`, { headers });
    }       
  
}
