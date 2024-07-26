import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { TokenService } from '../services/token/token-service.service';
import { catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private tokenService: TokenService,
    private router: Router
  ) { }

  intercept(req: HttpRequest<unknown>, next: HttpHandler) {
    let authToken: string = this.tokenService.getToken();

    console.log("the interceptor is working");

    if (authToken) {
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + authToken),
    }
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status == 403) {
          console.log('403 error');

          this.tokenService.removeToken();
          this.router.navigate(['/auth/login']);
        }
        return throwError(error);
      })
    );
  }
}
