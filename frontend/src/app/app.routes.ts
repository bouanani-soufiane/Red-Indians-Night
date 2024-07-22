import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: "auth",
    loadChildren: () => import ("./features/auth/admin.routes").then(m => m.AUTH_ROUTES)
  }
];
