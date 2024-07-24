package dev.codex.redindiansnight.User.Application.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.codex.redindiansnight.User.Application.DTOs.Requests.RoleRequest;
import dev.codex.redindiansnight.User.Application.Services.PermissionService;
import dev.codex.redindiansnight.User.Application.Services.RoleService;
import dev.codex.redindiansnight.User.Domain.Entities.Role;
import dev.codex.redindiansnight.User.Domain.Exceptions.RoleNotFoundException;
import dev.codex.redindiansnight.User.Infrastructure.RoleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final PermissionService permissionService;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));
    }

    @Override
    public Role findByName(String name) {
        return repository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }

    @Override
    public Role create(RoleRequest request) {
        final Role role = new Role(request.name());
        return repository.save(role);
    }

    @Override
    public Role update(Long id, RoleRequest request) {
        final Role role = repository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException(id));

        role.setName(request.name());
        return repository.save(role);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RoleNotFoundException(id);
        repository.deleteById(id);
    }
}
