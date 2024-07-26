import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { TokenService } from '../../services/token/token-service.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private tokenService: TokenService,
    private router: Router,
    private cookieService: CookieService
  ) { }

  intercept(req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<void>> {
    // let authToken: string = this.tokenService.getToken();

    let authToken: string = this.cookieService.get("accessToken");

    console.log("token ", authToken);


    if (authToken) {
    console.log("the interceptor is working");
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + authToken),
      });
      console.log(req);

    }
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status == 403) {
          console.log('403 error');

          this.tokenService.removeToken();
          // this.router.navigate(['/auth/login']);
        }
        return throwError(error);
      })
    );
  }
}
