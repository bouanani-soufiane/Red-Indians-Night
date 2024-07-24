import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environement } from '../../../../environement/Environement';
import { IRole } from '../../models/index';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleServiceService {
  private readonly baseUrl = environement.apiUrl + '/roles/';

  constructor(
    private http: HttpClient
  ) { }

  findRoleByName(roleName: string): Observable<IRole> {
    return this.http.get<IRole>(`${this.baseUrl}${roleName}`);
  }

  getIdByRoleName(roleName: string): Observable<number> {
    return this.findRoleByName(roleName).pipe(
      map((role: IRole) => role.id)
    );
  }
}