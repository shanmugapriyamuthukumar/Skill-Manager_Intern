import { Routes } from '@angular/router';
import { LoginComponent } from './features/login/login/login.component';

export const routes: Routes = [

	{
	    path: 'login',
	    loadComponent: () =>
	      import('./features/login/login/login.component').then(m => m.LoginComponent)
	  },

  {
    path: 'admin-dashboard',
    loadComponent: () =>
      import('./features/dashboard/admin-dashboard/admin-dashboard.component')
        .then(m => m.AdminDashboardComponent)
  },

  {
    path: 'employee-dashboard',
    loadComponent: () =>
      import('./features/dashboard/employee-dashboard/employee-dashboard.component')
        .then(m => m.EmployeeDashboardComponent)
  },
  {
    path: 'signup',
    loadComponent: () =>
      import('./features/signup/signup').then(m => m.SignupComponent)
  },
  
  {
    path: 'forgot-password',
    loadComponent: () =>
      import('./features/forgot-password/forgot-password')
        .then(m => m.ForgotPasswordComponent)
  },

  { path: '', redirectTo: 'login', pathMatch: 'full' }

];