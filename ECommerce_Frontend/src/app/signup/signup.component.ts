import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router, RouterLink, RouterOutlet } from '@angular/router';
import { AuthService } from '../services/auth/auth.service';
import { CommonModule } from '@angular/common';
import { DemoAngularMaterial } from '../DemoAngularMaterial';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [DemoAngularMaterial, RouterLink, CommonModule, RouterOutlet],
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  hidePassword = true;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.signupForm = this.fb.group({
      name: [null, Validators.required],
      email: [null, [Validators.required, Validators.email]],
      password: [null, [Validators.required]],
      confirmPassword: [null, [Validators.required]]
    });
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }

  onSubmit(): void {
    const password = this.signupForm.get('password')?.value;
    const confirmPassword = this.signupForm.get('confirmPassword')?.value;

    if (password !== confirmPassword) {
      this.snackBar.open('Passwords do not match!', 'Close', { duration: 5000, panelClass: 'error-snackBar' });
      return;
    } 
    this.authService.register(this.signupForm.value).subscribe(
      (response) => {
        this.snackBar.open('Signup successful!', 'Close', { duration: 5000, panelClass: 'success-snackBar '});
        this.router.navigateByUrl('/login');
      },
      (error) => {
        this.snackBar.open('Signup failed! Please try again.', 'Close', { duration: 5000, panelClass: 'error-snackBar' });
      }
    );
  }
}
