import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RoleService } from '../../../services/role/role-service.service';
import { Role } from '../../../models/role.model';
import { confirmPasswordValidator } from '../../../utils/PasswordValidator';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';
import { RegisterRequest } from '../../../shared/DTOs';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent implements OnInit {

  form !: FormGroup;
  roles: Role[] = [];

  constructor(
    private fb: FormBuilder,
    private roleService: RoleService,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
    this.getRoles();
  }

  getRoles() {
    this.roleService.getAll().subscribe(roles => {
      this.roles = roles;
    });
  }

  private initializeForm(): void {
    let { required, minLength, maxLength, email } = Validators;
    let validators = [required, minLength(3), maxLength(50)];

    this.form = this.fb.group({
      firstName: ['', validators],
      lastName: ['', validators],
      email: ['', [...validators, email]],
      password: ['', validators],
      confirmPassword: ['', validators],
      roleId: ["", required]
    }, confirmPasswordValidator());
  }

  onSubmit(): void {
    if (this.form.invalid) {
      console.log("the form is not valid");
      console.log(this.form.value);
      return;
    }
    this.authService.register(this.form.value as RegisterRequest);

  }


}
