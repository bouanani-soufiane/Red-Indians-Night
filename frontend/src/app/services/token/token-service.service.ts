import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtDecoderService } from '../jwtDecoder/jwt-decoder.service';
import { CookieContent } from '../../models';
import { AuthResponse } from '../../DTOs';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  cookieContent!: CookieContent;

  constructor(
    private cookieService: CookieService,
    private jwtDecoder: JwtDecoderService
  ) { }

  SetCookieContent(tokens: AuthResponse) {
    this.cookieContent = {
      accessToken: tokens.accessToken,
      refreshToken: tokens.refreshToken,
      user: this.jwtDecoder.decodeToken(tokens.accessToken),
    };

    this.SaveCookieContent();
  }


  SaveCookieContent() {
    const expirationDate = new Date();
    expirationDate.setDate(expirationDate.getDate() + 1);
    this.cookieService.set("accessToken", this.cookieContent.accessToken, expirationDate, '/', undefined, true, 'Strict');
    this.cookieService.set("refreshToken", this.cookieContent.refreshToken, expirationDate, '/', undefined, true, 'Strict');
    this.cookieService.set("user", JSON.stringify(this.cookieContent.user), expirationDate, '/', undefined, true, 'Strict');
  }

  getToken(): string {
    return this.cookieService.get("accessToken");
  }

  getUser() {
    return JSON.parse(this.cookieService.get("user"));
  }

  getUserId() {
    let user = JSON.parse(this.getUser());
    return user.id;
  }

  removeToken() {
    this.cookieService.deleteAll();
  }
}
