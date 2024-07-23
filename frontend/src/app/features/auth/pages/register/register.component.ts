import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.intitializeForm();
  }

  private intitializeForm(): void {
    const { required, minLength, maxLength, email } = Validators;

    this.registerForm = this.fb.group({
      firstName: ["", [required, minLength(3), maxLength(15)]],
      lastName: ["", [required, minLength(3), maxLength(15)]],
      email: ["", [required, email]],
      password: ["", required, minLength(6), maxLength(30)],
      passwordConfirmation: ["", [required]],
    });
  }

  onSubmit(): void {
    if (this.registerForm.valid)

      console.log("the form is valid");
    else {
      console.log(this.registerForm.value);
      console.log("this form is not valid");
    }

  }
}
