import { Routes } from "@angular/router";

export const ADMIN_ROUTES: Routes = [
  {
    path: "",
    loadComponent: () => import("./event/event-listing/event-listing.component").then(m => m.EventListingComponent),
  },
  {
    path: "events",
    loadComponent: () => import("./event/event-listing/event-listing.component").then(m => m.EventListingComponent),
  }
];

