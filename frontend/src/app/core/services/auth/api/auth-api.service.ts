import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterRequest } from '../../../../DTOs/auth/requests/register-request';
import { AuthResponse } from '../../../../DTOs/auth/responses/auth-response';
import { environement } from '../../../../environement/Environement';
import { LoginRequest } from '../../../../DTOs/auth/requests/login-request';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  private readonly baseUrl: string = `${environement.apiUrl}/auth/`;

  constructor(
    private http: HttpClient
  ) { }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}register`, request);
  }

  login (request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}authenticate`, request);
  }

  logout (): Observable<void> {
    return this.http.get<void>(`${this.baseUrl}logout`);
  }
}
