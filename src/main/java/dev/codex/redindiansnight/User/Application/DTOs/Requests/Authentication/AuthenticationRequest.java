package dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;

  @Size(min = 8, max = 30, message = "The password should be between 8 and 30 characters!")
  String password;
}
