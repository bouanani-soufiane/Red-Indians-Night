package dev.codex.redindiansnight.Event.Application.Services.Impl;

import dev.codex.redindiansnight.Event.Application.Services.EventQuestionService;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import dev.codex.redindiansnight.Event.Domain.Entities.EventQuestion;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;
import dev.codex.redindiansnight.Event.Infrastructure.EventQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class EventQuestionServiceImpl implements EventQuestionService {
    private final EventQuestionRepository repository;

    public void createAll(Event savedEvent, List<Question> questions) {
        final List<EventQuestion> eventQuestions = questions.stream()
                .map(question -> new EventQuestion(savedEvent, question))
                .toList();
        repository.saveAll(eventQuestions);
    }

    public EventQuestion findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("EventQuestion with the id " + id + " not found"));
    }
}
