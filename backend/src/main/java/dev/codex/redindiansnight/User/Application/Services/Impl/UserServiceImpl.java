package dev.codex.redindiansnight.User.Application.Services.Impl;

import dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication.RegisterRequest;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.UserRequest;
import dev.codex.redindiansnight.User.Application.Services.PermissionService;
import dev.codex.redindiansnight.User.Application.Services.RoleService;
import dev.codex.redindiansnight.User.Application.Services.UserService;
import dev.codex.redindiansnight.User.Domain.Entities.Permission;
import dev.codex.redindiansnight.User.Domain.Entities.Role;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import dev.codex.redindiansnight.User.Domain.Exceptions.DuplicateEmailException;
import dev.codex.redindiansnight.User.Domain.Exceptions.UserNotFoundException;
import dev.codex.redindiansnight.User.Infrastructure.TokenRepository;
import dev.codex.redindiansnight.User.Infrastructure.UserRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final RoleService roleService;
    private final PermissionService permissionService;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepository tokenRepository;


    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public User create(RegisterRequest request) {
        return create(
                new UserRequest(
                        request.firstName(),
                        request.lastName(),
                        request.email(),
                        request.password(),
                        request.roleId(),
                        new ArrayList<>()
                )
        );
    }


    @Override
    @Transactional
    public User create(UserRequest request) {
        try {
            final Role role = roleService.findById(request.roleId());
            final List<Permission> permissions = permissionService.findAllByIds(request.permissionIds());
            final String encodedPassword = passwordEncoder.encode(request.password());
            final User user = new User(request.email(), request.firstName(), request.lastName(), encodedPassword, role, permissions);
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                ConstraintViolationException cve = (ConstraintViolationException) e.getCause();
                if (cve.getConstraintViolations() != null && cve.getConstraintViolations().equals("users_email_key")) {
                    throw new DuplicateEmailException("Email already exists: " + request.email());
                }
            }
            throw e;
        }
    }

    @Override
    @Transactional
    public User update(Integer id, UserRequest request) {
        try {
            final User user = repository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException(id));
            final Role role = roleService.findById(request.roleId());
            final List<Permission> permissions = permissionService.findAllByIds(request.permissionIds());
            final String encodedPassword = passwordEncoder.encode(request.password());

            user.setFirstName(request.firstName());
            user.setLastName(request.lastName());
            user.setEmail(request.email());
            user.setPassword(encodedPassword);
            user.setRole(role);
            user.setPermissions(permissions);
            return repository.save(user);
        } catch (DataIntegrityViolationException e) {
            if (e.getMostSpecificCause().getMessage().contains("users_email_key")) {
                throw new DuplicateEmailException("Email already exists : " + request.email());
            }
            System.out.println("here here here");
            System.out.println(e.getMostSpecificCause().getMessage().contains("users_email_key"));
            throw e;
        }
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id))
            throw new UserNotFoundException(id);
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        tokenRepository.deleteAllByUser(user);
        repository.delete(user);
    }
}
