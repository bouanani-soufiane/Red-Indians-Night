package dev.codex.redindiansnight.Event.Infrastructure;

import dev.codex.redindiansnight.Event.Domain.Entities.EventQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventQuestionRepository extends JpaRepository<EventQuestion, Long> {
}