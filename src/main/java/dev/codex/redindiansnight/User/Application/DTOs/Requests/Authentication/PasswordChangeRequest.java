package dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PasswordChangeRequest {
    private String currentPassword;
    private String newPassword;
}
