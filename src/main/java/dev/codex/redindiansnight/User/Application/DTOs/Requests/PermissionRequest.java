package dev.codex.redindiansnight.User.Application.DTOs.Requests;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link dev.codex.redindiansnight.User.Domain.Entities.Permission}
 */
public record PermissionRequest(
        @NotBlank
        String name
) implements Serializable {
}