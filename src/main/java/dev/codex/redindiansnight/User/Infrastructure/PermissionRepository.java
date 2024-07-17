package dev.codex.redindiansnight.User.Infrastructure;

import dev.codex.redindiansnight.User.Domain.Entities.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}