package dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "email is required")
        @Email(message = "the email sould be valid") String email,
        @Size(min = 8, max = 30, message = "The password should be between 8 and 30 characters!") String password,
        @NotNull(message = "role is required")
        Long roleId
) { }

