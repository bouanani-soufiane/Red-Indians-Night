import { Injectable } from '@angular/core';
import { AuthApiService } from './api/auth-api.service';
import { RegisterRequest } from '../../../DTOs/auth/requests/register-request';
import { AuthResponse } from '../../../DTOs/auth/responses/auth-response';
import { TokenService } from '../token/token-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private authApiService: AuthApiService,
    private tokenService: TokenService
  ) { }

  register(request: RegisterRequest) {
    this.authApiService.register(request).subscribe((res: AuthResponse) => {
      this.tokenService.SetCookieContent(res);
    });
  }
}
