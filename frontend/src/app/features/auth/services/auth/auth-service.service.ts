import { Injectable } from '@angular/core';
import { environement } from '../../../../environement/Environement';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  AuthResponse, RegisterRequest, LoginRequest
} from '../../models/index';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private readonly REGISTER_URL: string = `${environement.apiUrl}auth/regiter`;
  private readonly LOGIN_URL: string = `${environement.apiUrl}auth/auth`;

  constructor(private http: HttpClient) { }

  register(request: RegisterRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.REGISTER_URL, request);
  }

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(this.LOGIN_URL, request);
  }
}
