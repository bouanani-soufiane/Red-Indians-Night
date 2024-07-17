package dev.codex.redindiansnight.User.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.PermissionRequest;
import dev.codex.redindiansnight.User.Domain.Entities.Permission;

import java.util.List;

public interface PermissionService extends CrudService<Permission, Long, PermissionRequest> {
    List<Permission> findAllByIds(List<Long> longs);
}
