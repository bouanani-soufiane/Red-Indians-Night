package dev.codex.redindiansnight.Event.Application.Services.Impl;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.EventRequest;
import dev.codex.redindiansnight.Event.Application.Services.EventService;
import dev.codex.redindiansnight.Event.Application.Services.QuestionService;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import dev.codex.redindiansnight.Event.Domain.Entities.EventQuestion;
import dev.codex.redindiansnight.Event.Infrastructure.EventQuestionRepository;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;
import dev.codex.redindiansnight.Event.Domain.Exceptions.EventNotFoundException;
import dev.codex.redindiansnight.Event.Infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final QuestionService questionService;
    private final EventQuestionRepository eventQuestionRepository;

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Event findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    public Event create(EventRequest request) {
        List<Question> questions = questionService.findAllById(request.questionIds());
        questions.addAll(questionService.createAll(request.newQuestions()));

        Event event = new Event(
                request.title(),
                request.description(),
                request.price(),
                request.location(),
                request.startDate(),
                request.endDate(),
                request.numberOfAttendees(),
                request.isLive()
        );
        Event savedEvent = repository.save(event);
        setQuestions(savedEvent, questions);
        return savedEvent;
    }

    @Override
    public Event update(Long id, EventRequest request) {
        Event event = repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));

        event.setTitle(request.title());
        event.setDescription(request.description());
        event.setPrice(request.price());
        event.setLocation(request.location());
        event.setStartDate(request.startDate());
        event.setEndDate(request.endDate());
        event.setNumberOfAttendees(request.numberOfAttendees());
        event.setIsLive(request.isLive());
        return repository.save(event);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new EventNotFoundException(id);
        repository.deleteById(id);
    }

    private void setQuestions(Event savedEvent, List<Question> questions) {
        List<EventQuestion> eventQuestions = questions.stream()
                .map(question -> new EventQuestion(savedEvent, question))
                .toList();
        eventQuestionRepository.saveAll(eventQuestions);
    }
}
