import { Injectable } from '@angular/core';
import { AuthApiService } from './api/auth-api.service';
import { TokenService } from '../token/token-service.service';
import { Router } from '@angular/router';
import { RegisterRequest, LoginRequest ,AuthResponse} from '../../shared/DTOs';
import { DecodedToken } from '../../shared/models';
import { roleRouteMap } from '../../utils/RoutesByRoleMap';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private authApiService: AuthApiService,
    private tokenService: TokenService,
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

    if (!path)
      console.log("the role is not defined in the roleRouteMap");

    this.router.navigate([path]);
  }
}
