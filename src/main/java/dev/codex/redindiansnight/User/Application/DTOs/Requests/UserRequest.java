package dev.codex.redindiansnight.User.Application.DTOs.Requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link dev.codex.cinestar.User.Domain.Entities.User}
 */
public record UserRequest(
        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6, max = 20)
        String password,

        @NotNull
        Long roleId,

        @NotNull
        List<Long> permissionIds
) implements Serializable {
}