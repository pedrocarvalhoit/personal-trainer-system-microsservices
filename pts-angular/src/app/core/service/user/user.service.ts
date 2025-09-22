import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../../../auth/services/error/error-handler.service';
import { Observable } from 'rxjs';

export interface Athlete {
  id: string;
  photo: string | null;
  fullName: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  dateOfBirth: string;
  age: string;
  gender: string;
  enabled: boolean;
}

export interface PageResponse<T> {
  content: T[];
  pageNumber: number;
  size: number;
  totalElements: number;
  totalPages: number;
  first: boolean;
  last: boolean;
}

interface UserDataForNavbarResponseDto {
  firstName: string;
  photo: string;
}

interface UserEditDataDto {
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;
  phone: string;
  gender: string;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:9000/api/v1/user';

  constructor(
    private http: HttpClient,
    public jwtHelper: JwtHelperService,
    private errorHandlerService: ErrorHandlerService
  ) { }

  updateUser(
    headers: HttpHeaders,
    firstName: string,
    lastName: string,
    dateOfBirth: string,
    phone: string,
    gender: string
  ) {
    return this.http.patch(
      `${this.baseUrl}/update-user-data`,
      { firstName, lastName, dateOfBirth, phone, gender },
      { headers, responseType: 'text' }
    );
  }

  updateAthlete(headers: HttpHeaders, athleteId: string, athleteData: Partial<Athlete>) {
    return this.http.patch(
      `${this.baseUrl}/update-athlete-data/${athleteId}`,
      athleteData,
      { headers, responseType: 'text' }
    );
  }

  getUserDataForNavbar(headers: HttpHeaders): Observable<UserDataForNavbarResponseDto> {
    return this.http.get<UserDataForNavbarResponseDto>(`${this.baseUrl}/get-user-data-for-navbar`, { headers });
  }

  updatePhoto(headers: HttpHeaders, formData: FormData): Observable<string> {
    return this.http.patch(`${this.baseUrl}/upload-photo`, formData, { headers, responseType: 'text' });
  }

  updateAthletePhoto(headers: HttpHeaders, formData: FormData, athleteId: string): Observable<string> {
    return this.http.patch(`${this.baseUrl}/upload-athlete-photo/${athleteId}`, formData, { headers, responseType: 'text' });
  }

  getCurrentUserData(headers: HttpHeaders): Observable<UserEditDataDto> {
    return this.http.get<UserEditDataDto>(`${this.baseUrl}/get-user-data`, { headers });
  }

  getAthleteById(headers: HttpHeaders, athleteId: string): Observable<Athlete> {
    return this.http.get<Athlete>(`${this.baseUrl}/get-athlete-data/${athleteId}`, { headers });
  }

  getAllEnabledAthletes(headers: HttpHeaders, page: number = 0, size: number = 50): Observable<PageResponse<Athlete>> {
    return this.http.get<PageResponse<Athlete>>(
      `${this.baseUrl}/get-enabled-athletes`,
      { headers, params: { page: page.toString(), size: size.toString() } }
    );
  }

  getAllDisabledAthletes(headers: HttpHeaders, page: number = 0, size: number = 50): Observable<PageResponse<Athlete>> {
    return this.http.get<PageResponse<Athlete>>(
      `${this.baseUrl}/get-disabled-athletes`,
      { headers, params: { page: page.toString(), size: size.toString() } }
    );
  }

  changeStatus(headers: HttpHeaders, athleteId: string): Observable<Athlete> {
    return this.http.patch<Athlete>(`${this.baseUrl}/change-status/${athleteId}`, {}, { headers });
  }

}
