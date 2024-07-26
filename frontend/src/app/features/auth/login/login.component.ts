import { Component } from '@angular/core';
import { confirmPasswordValidator } from '../../../utils/PasswordValidator';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Role } from '../../../models/role.model';
import { AuthService } from '../../../core/services/auth/auth.service';
import { RoleService } from '../../../services/role/role-service.service';
import { LoginRequest } from '../../../DTOs/auth/requests/login-request';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

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
