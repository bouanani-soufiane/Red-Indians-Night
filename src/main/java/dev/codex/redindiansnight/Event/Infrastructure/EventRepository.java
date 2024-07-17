package dev.codex.redindiansnight.Event.Infrastructure;

import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}