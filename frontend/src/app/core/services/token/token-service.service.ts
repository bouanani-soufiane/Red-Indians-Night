import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtDecoderService } from '../jwtDecoder/jwt-decoder.service';
import { AuthResponse } from '../../../DTOs/auth/responses/auth-response';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  accessToken!: string;
  refreshToken!: string;
  user!: any;

  constructor(
    private cookieService: CookieService,
    private jwtDecoder: JwtDecoderService
  ) { }

  extartUserInfo(tokens: AuthResponse) {
    this.accessToken = tokens.accessToken;
    this.refreshToken = tokens.refreshToken;
    this.user = this.jwtDecoder.decodeToken(tokens.accessToken);
  }

  saveUserInfo() {
    let { set } = this.cookieService;
    set("accessToken", this.accessToken);
    set("refreshToken", this.refreshToken);
    set("user", this.user);
  }

  getToken(): string {
    return this.cookieService.get("accessToken");
  }

  getUser() {
    return this.user;
  }

  getUserId() {
    return this.user.id;
  }
}
