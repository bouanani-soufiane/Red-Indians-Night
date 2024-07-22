import { Injectable } from '@angular/core';
import { environement } from '../../../../environement/Environement';
import { HttpClient } from '@angular/common/http';
import { RegisterRequest } from '../../models/register-request';
import { Observable } from 'rxjs';
import { AuthResponse } from '../../models/auth-response';
import { LoginRequest } from '../../models/login-request';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  private REGISTER_URL: string = `${environement.apiUrl}auth/regiter`;
  private LOGIN_URL: string = `${environement.apiUrl}auth/auth`;

  constructor(private http: HttpClient) { }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.REGISTER_URL, request);
  }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.LOGIN_URL, request);
  }
}
