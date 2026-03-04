import { Component } from '@angular/core';
import { FormBuilder, Validators, ReactiveFormsModule, FormGroup } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './forgot-password.html'
})
export class ForgotPasswordComponent {

  resetForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {

    this.resetForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      newPassword: ['', Validators.required]
    });

  }

  resetPassword() {

    if (this.resetForm.invalid) return;

    this.http.post(
      "http://localhost:9092/api/auth/reset-password",
      this.resetForm.value,
      { responseType: 'text' }
    ).subscribe({
      next: () => {
        alert("Password updated successfully");
        this.router.navigateByUrl('/login');
      },
      error: err => {
        console.log(err);
        alert("Email not found");
      }
    });

  }

}