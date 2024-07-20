
package dev.codex.redindiansnight.Event.Infrastructure;

import dev.codex.redindiansnight.Event.Domain.Entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}