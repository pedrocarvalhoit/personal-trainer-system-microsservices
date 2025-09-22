import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { ErrorHandlerService } from '../error/error-handler.service';
import { Observable } from 'rxjs';

export const TOKEN_NAME: string = 'jwt_token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  errorMessage: string = '';

  constructor(private http: HttpClient, public jwtHelper: JwtHelperService, private errorHandlerService: ErrorHandlerService) { }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>('http://localhost:9000/api/v1/authentication/login', {email, password});
  }

  register(userData: any) {
  return this.http.post<any>('http://localhost:9000/api/v1/authentication/register', userData);
}

  public setToken(token: string): void {
    if (typeof localStorage !== 'undefined' && localStorage !== null) {
      localStorage.setItem(TOKEN_NAME, token);
    }
  }

  public getToken(): string {
    if (typeof localStorage !== 'undefined' && localStorage !== null) {
      return localStorage.getItem(TOKEN_NAME) || '';
    }
    return '';
  }

  public logout(): void {
    if (typeof localStorage !== 'undefined' && localStorage !== null) {
      localStorage.removeItem(TOKEN_NAME);
    }
  }

    public isAuthenticated(): boolean {
    if (typeof localStorage !== 'undefined' && localStorage !== null) {
      const token = localStorage.getItem(TOKEN_NAME);
      return !this.jwtHelper.isTokenExpired(token);
    }
    return false;
  }

  public getLoggedUserId(): string | null {
  const token = this.getToken();
  if (!token) return null;

  try {
    const decodedToken: any = this.jwtHelper.decodeToken(token);
    return decodedToken.id || null; // aqui vai pegar o userId
  } catch (error) {
    console.error('Erro ao decodificar JWT:', error);
    return null;
  }
}


}
