import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, Validators, FormGroup } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './signup.html',
  styleUrls: ['./signup.css']
})
export class SignupComponent {

  error = "";
  signupForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router
  ) {

    this.signupForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });

  }

  onSignup() {

    if (this.signupForm.invalid) return;

    this.http.post(
      "http://localhost:9092/api/auth/signup",
      this.signupForm.value,
      { responseType: 'text' }   
    ).subscribe({
      next: () => {

        alert("Signup successful!");

        this.router.navigateByUrl('/login');

      },
      error: err => {
        console.log(err);
        this.error = "Signup failed";
      }
    });

  }
}