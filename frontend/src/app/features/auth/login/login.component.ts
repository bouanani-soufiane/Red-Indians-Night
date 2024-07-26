import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { LoginRequest } from '../../../shared/DTOs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  form !: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  private initializeForm(): void {
    let { required, minLength, maxLength, email } = Validators;
    let validators = [required, minLength(3), maxLength(50)];

    this.form = this.fb.group({
      email: ['', [...validators, email]],
      password: ['', validators],
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      console.log("the form is not valid");
      console.log(this.form.value);
      return;
    }

    this.authService.login(this.form.value as LoginRequest);
  }
}
