import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { JwtDecoderService } from '../jwtDecoder/jwt-decoder.service';
import { AuthResponse } from '../../../DTOs/auth/responses/auth-response';
import { CookieContent } from '../../../models/cookie-content.model';
import { DecodedToken } from '../../../models/decoded-token.model';

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
    this.cookieService.set("accessToken", this.cookieContent.accessToken);
    this.cookieService.set("refreshToken", this.cookieContent.refreshToken);
    this.cookieService.set("user", JSON.stringify(this.cookieContent.user));
  }

  getToken(): string {
    return this.cookieService.get("accessToken");
  }

  getUser() {
    return this.cookieService.get("user");
  }

  getUserId() {
    let user = JSON.parse(this.getUser());
    return user.id;
  }
}
