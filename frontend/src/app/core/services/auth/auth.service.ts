import { Injectable } from '@angular/core';
import { AuthApiService } from './api/auth-api.service';
import { RegisterRequest } from '../../../DTOs/auth/requests/register-request';
import { AuthResponse } from '../../../DTOs/auth/responses/auth-response';
import { TokenService } from '../token/token-service.service';
import { DecodedToken } from '../../../models/decoded-token.model';
import { Router } from '@angular/router';
import { LoginRequest } from '../../../DTOs/auth/requests/login-request';
import { RoleService } from '../../../services/role/role-service.service';
import { Role, roleRouteMap } from '../../../models/role.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private authApiService: AuthApiService,
    private tokenService: TokenService,
    private roleService: RoleService,
    private router: Router
  ) { }

  register(credentials: RegisterRequest) {
    this.authApiService.register(credentials).subscribe(
      (res: AuthResponse) => {
        this.handleAuthentication(res);
      },
      (error) => {
        console.log("the registration failed");
        console.error(error.error.message);
      });
  }

  login(credentials: LoginRequest) {
    this.authApiService.login(credentials).subscribe(
      (res: AuthResponse) => {
        this.handleAuthentication(res);
      },
      (error) => {
        console.log("the login failed");
        console.error(error.error.message);
      });
  }

  private handleAuthentication(res: AuthResponse) {
    this.tokenService.SetCookieContent(res);
    let decodedToken: DecodedToken = this.tokenService.getUser();

    let path: string = roleRouteMap[decodedToken.role]
    if (path)
      this.router.navigate([path]);
    else
      console.log("the role is not defined in the roleRouteMap");

  }
}
