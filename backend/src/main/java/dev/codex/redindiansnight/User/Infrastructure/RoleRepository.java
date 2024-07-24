package dev.codex.redindiansnight.User.Infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.codex.redindiansnight.User.Domain.Entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
