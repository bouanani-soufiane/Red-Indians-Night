import { JwtPayload } from "jwt-decode";
import { DecodedToken } from "./decoded-token.model";

export interface CookieContent {
  accessToken: string;
  refreshToken: string;
  user: JwtPayload | DecodedToken;
}
