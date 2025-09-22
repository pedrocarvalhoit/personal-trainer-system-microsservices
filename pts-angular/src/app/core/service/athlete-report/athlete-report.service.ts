import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { Observable } from 'rxjs';

export interface AthleteReport{
  id: number;
  createdAt: string;
  bloodPressureStatus: string;
  waistHipRisk: string;
  bmistatus: string;
  
}

@Injectable({
  providedIn: 'root'
})
export class AthleteReportService {

   private baseUrl = 'http://localhost:9000/api/v1/athlete-report';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) { }
  
  generateForAthlete(headers: HttpHeaders, athleteId: string): Observable<number> {
            return this.http.post<number>(`${this.baseUrl}/generate-report/${athleteId}`, null, { headers });
          }


  getLastForAthlete(headers: HttpHeaders, athleteId: string): Observable<AthleteReport> {
            return this.http.get<AthleteReport>(`${this.baseUrl}/last-for-athlete/${athleteId}`, { headers });
          }
}
