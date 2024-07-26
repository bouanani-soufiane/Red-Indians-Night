import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../../../environement/Environement';
import { RegisterRequest, LoginRequest, AuthResponse } from '../../../shared/DTOs';

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

  login(request: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}authenticate`, request);
  }

  logout(): Observable<void> {
    return this.http.get<void>(`${this.baseUrl}logout`);
  }
}
