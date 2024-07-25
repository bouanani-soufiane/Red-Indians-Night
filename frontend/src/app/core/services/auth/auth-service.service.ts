import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthResponse } from '../../../DTOs/auth/responses/auth-response';
import { RegisterRequest } from '../../../DTOs/auth/requests/register-request';
import { environement } from '../../../environement/Environement';
import { LoginRequest } from '../../../DTOs/auth/requests/login-request';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseUrl: string = `${environement.apiUrl}/auth/`;

  constructor(
    private http: HttpClient
  ) { }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}register`, request);
  }

  login (request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}login`, request);
  }

  logout (): Observable<void> {
    return this.http.get<void>(`${this.baseUrl}logout`);
  }
}
