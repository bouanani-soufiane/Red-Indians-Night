import { Routes } from '@angular/router';
import { DashboardLayoutComponent } from './layouts/dashboard-layout/dashboard-layout.component';

export const routes: Routes = [
  {
    path: "auth",
    loadChildren: () => import("./features/auth/auth.routes").then(m => m.AUTH_ROUTES)
  },
  {
    path: "dashboard",
    component: DashboardLayoutComponent,
    children: [
      {
        path: "",
        loadChildren: () => import("./features/administration/administration.routes").then(m => m.ADMIN_ROUTES),
      },
    ]
  }
];

