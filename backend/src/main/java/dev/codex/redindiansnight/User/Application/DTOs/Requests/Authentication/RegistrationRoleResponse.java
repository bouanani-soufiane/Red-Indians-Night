package dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication;

import java.io.Serializable;

import dev.codex.redindiansnight.User.Domain.Entities.Role;

/**
 * RegistrationRoleResponse
 */
public record RegistrationRoleResponse(
        Long id,
        String name) implements Serializable {
    public static RegistrationRoleResponse fromRole(Role role) {
        return new RegistrationRoleResponse(role.getId(), role.getName());
    } 
}
