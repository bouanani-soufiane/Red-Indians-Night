package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import dev.codex.redindiansnight.Event.Domain.Entities.EventQuestion;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;

import java.util.List;

public interface EventQuestionService {
    void createAll(Event savedEvent, List<Question> questions);

    EventQuestion findById(Long id);
}
