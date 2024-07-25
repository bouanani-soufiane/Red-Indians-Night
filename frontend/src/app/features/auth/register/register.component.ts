import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RoleService } from '../../../services/role/role-service.service';
import { Role } from '../../../models/role.model';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {

  form !: FormGroup;
  roles: Role[] = [];

  constructor(
    private fb: FormBuilder,
    private roleService: RoleService
  ) { }

  ngOnInit(): void {
    this.initializeForm();
  }

  getRoles () {
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
      roleId: ["", required]
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      console.log("the form is not valid");
      return;
    }

    console.log(this.form.value);
  }


}
