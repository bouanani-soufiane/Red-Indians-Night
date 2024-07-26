package dev.codex.redindiansnight.User.Interfaces.web;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.codex.redindiansnight.User.Application.DTOs.Requests.RoleRequest;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication.RegistrationRoleResponse;
import dev.codex.redindiansnight.User.Application.Services.RoleService;
import dev.codex.redindiansnight.User.Domain.Entities.Role;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> findAll() {
        final List<Role> roles = roleService.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/signup")
    public ResponseEntity<List<RegistrationRoleResponse>> findAllForSignup() {
        final List<RegistrationRoleResponse> roles = roleService.getRoleForSignup();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable Long id) {
        final Role role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Role> findByName(@PathVariable String name) {
        final Role role = roleService.findByName(name);
        System.out.println("this is the method with name");
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody @Valid RoleRequest dto) {
        final Role createdRole = roleService.create(dto);
        return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody @Valid RoleRequest dto) {
        final Role updatedRole = roleService.update(id, dto);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
