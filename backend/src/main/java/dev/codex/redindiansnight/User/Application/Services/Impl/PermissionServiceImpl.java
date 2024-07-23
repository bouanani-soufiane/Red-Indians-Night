package dev.codex.redindiansnight.User.Application.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.codex.redindiansnight.User.Application.DTOs.Requests.PermissionRequest;
import dev.codex.redindiansnight.User.Application.Services.PermissionService;
import dev.codex.redindiansnight.User.Domain.Entities.Permission;
import dev.codex.redindiansnight.User.Domain.Exceptions.PermissionNotFoundException;
import dev.codex.redindiansnight.User.Infrastructure.PermissionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository repository;

    public List<Permission> findAll() {
        return repository.findAll();
    }

    public List<Permission> findAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }

    public Permission findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
    }

    public Permission create(PermissionRequest request) {
        final Permission permission = new Permission(request.name());
        return repository.save(permission);
    }

    public Permission update(Long id, PermissionRequest request) {
        final Permission permission = repository.findById(id)
                .orElseThrow(() -> new PermissionNotFoundException(id));
        permission.setName(request.name());
        return repository.save(permission);
    }

    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new PermissionNotFoundException(id);
        repository.deleteById(id);
    }
}
