package dev.codex.redindiansnight.Event.Infrastructure;

import dev.codex.redindiansnight.Event.Domain.Entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByEventQuestionId(Long eventQuestionId);
}