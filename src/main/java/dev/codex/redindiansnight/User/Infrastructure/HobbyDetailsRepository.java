package dev.codex.redindiansnight.User.Infrastructure;

import dev.codex.redindiansnight.User.Domain.Entities.HobbyDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyDetailsRepository extends JpaRepository<HobbyDetails, Long> {
}