package dev.codex.redindiansnight.Event.Infrastructure;

import dev.codex.redindiansnight.Event.Domain.Entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}