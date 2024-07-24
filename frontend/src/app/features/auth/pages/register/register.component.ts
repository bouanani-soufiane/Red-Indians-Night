import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { confirmPasswordValidator } from '../../../../utils/PasswordValidator';
import { RegisterRequest } from '../../models/index';
import { RoleServiceService } from '../../../shared/services/role/role-service.service';
import { AuthService } from '../../services/auth/auth-service.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  providers: [AuthService],
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
    this.intitializeForm();
  }

  private intitializeForm(): void {
    const { required, email } = Validators;

    this.registerForm = this.fb.group({
      firstName: ['', required],
      lastName: ['', required],
      email: ['', [required, email]],
      password: ['', required],
      passwordConfirmation: ['', [required]],
    },
      { validators: confirmPasswordValidator() });
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      console.log("the form is not valid");
      console.log(this.registerForm.errors);
      return;
    }

    let roleName: string = this.registerForm.get('role')?.value;

    let registerRequest: RegisterRequest = {
      firstName: this.registerForm.get('firstName')?.value,
      lastName: this.registerForm.get('lastName')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
      roleId: this.getRoleId(roleName),
    };
    console.log(registerRequest);
  }

  private getRoleId(roleName: string): number {
    // this.roleService.getIdByRoleName(roleName).subscribe(
    //   id => {
    //     console.log('Role ID:', id);
    //     return id;
    //   },
    //   error => console.error('Error:', error)
    // );
    return -1;
  }
}
