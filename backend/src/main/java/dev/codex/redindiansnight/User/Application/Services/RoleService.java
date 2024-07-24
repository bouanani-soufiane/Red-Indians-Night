package dev.codex.redindiansnight.User.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.RoleRequest;
import dev.codex.redindiansnight.User.Domain.Entities.Role;

public interface RoleService extends CrudService<Role, Long, RoleRequest> {
    Role findByName(String name);
}
