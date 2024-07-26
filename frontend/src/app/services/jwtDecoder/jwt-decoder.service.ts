import { Injectable } from '@angular/core';
import { jwtDecode } from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class JwtDecoderService {

  constructor() { }

  decodeToken(token: string) {
    return jwtDecode(token);
  }
}
