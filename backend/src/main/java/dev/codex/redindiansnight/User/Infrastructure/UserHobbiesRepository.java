package dev.codex.redindiansnight.User.Infrastructure;

import dev.codex.redindiansnight.User.Domain.Entities.UserHobbies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHobbiesRepository extends JpaRepository<UserHobbies, Long> {
}