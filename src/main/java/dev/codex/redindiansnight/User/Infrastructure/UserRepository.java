package dev.codex.redindiansnight.User.Infrastructure;

import dev.codex.redindiansnight.User.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}