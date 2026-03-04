import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {

  private baseUrl = 'http://localhost:9092/api/auth';

  constructor(private http: HttpClient,
              private router: Router) {}

  login(email: string, password: string) {
    return this.http.post<any>(`${this.baseUrl}/login`, {
      email,
      password
    });
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  saveRole(role: string) {
    localStorage.setItem('role', role);
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}