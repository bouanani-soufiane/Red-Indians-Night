package dev.codex.redindiansnight.Event.Application.Services.Impl;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.EventRequest;
import dev.codex.redindiansnight.Event.Application.Services.EventQuestionService;
import dev.codex.redindiansnight.Event.Application.Services.EventService;
import dev.codex.redindiansnight.Event.Application.Services.QuestionService;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;
import dev.codex.redindiansnight.Event.Domain.Exceptions.EventNotFoundException;
import dev.codex.redindiansnight.Event.Infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class EventServiceImpl implements EventService {
    private final EventRepository repository;
    private final QuestionService questionService;
    private final EventQuestionService eventQuestionService;

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Event> findAll(int pageNum, int pageSize ) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return repository.findAll(pageable);
    }

    @Override
    public Event findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    @Override
    @Transactional
    public Event create(EventRequest request) {
        final List<Question> questions = questionService.findAllById(request.questionIds());
        questions.addAll(questionService.createAll(request.newQuestions()));

        final Event event = new Event(
                request.title(),
                request.description(),
                request.price(),
                request.location(),
                request.startDate(),
                request.endDate(),
                request.numberOfAttendees(),
                request.isLive()
        );
        final Event savedEvent = repository.save(event);
        eventQuestionService.createAll(savedEvent, questions);
        return savedEvent;
    }

    @Override
    @Transactional
    public Event update(Long id, EventRequest request) {
        final Event event = repository.findById(id)
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
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new EventNotFoundException(id);
        repository.deleteById(id);
    }
}
