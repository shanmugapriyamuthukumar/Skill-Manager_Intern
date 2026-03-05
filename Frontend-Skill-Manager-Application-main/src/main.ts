import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter, Routes, withComponentInputBinding } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

// FORMS (choose ONE of the two blocks below depending on your Angular version)
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms'; // Angular 15/16/17 compatible

// If you are on Angular 17+, you could alternatively do:
// import { provideForms } from '@angular/forms';

import { AppComponent } from './app/app.component';
import { AdminDashboardComponent } from './app/features/dashboard/admin-dashboard/admin-dashboard.component';

// Define routes (root goes to admin-dashboard)
const routes: Routes = [
  { path: '', redirectTo: 'admin-dashboard', pathMatch: 'full' },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: '**', redirectTo: 'admin-dashboard' }
];

bootstrapApplication(AppComponent, {
  providers: [
    provideRouter(routes, withComponentInputBinding()),
    provideHttpClient(),

    // ✅ EITHER this (works for Angular 15/16/17):
    importProvidersFrom(FormsModule),

    // ❌ OR this (Angular 17+ only):
    // provideForms(),
  ]
}).catch(err => console.error(err));