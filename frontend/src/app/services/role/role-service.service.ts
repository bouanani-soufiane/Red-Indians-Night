import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environement } from '../../environement/Environement';
import { Observable } from 'rxjs';
import { Role } from '../../models/role.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private readonly baseUrl: string = `${environement.apiUrl}/roles`

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Role[]> {
    return this.http.get<Role[]>(this.baseUrl);
  }

  getAllForSignup(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.baseUrl}/signup`);
  }

  getById(id: number): Observable<Role> {
    return this.http.get<Role>(`${this.baseUrl}/${id}`);
  }

  create(role: Role): Observable<Role> {
    return this.http.post<Role>(this.baseUrl, role);
  }

  update(id: number, request: Role): Observable<Role> {
    return this.http.patch<Role>(`${this.baseUrl}/${id}`, request);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
