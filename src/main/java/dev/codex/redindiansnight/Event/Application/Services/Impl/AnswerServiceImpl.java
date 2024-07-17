package dev.codex.redindiansnight.Event.Application.Services.Impl;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.AnswerRequest;
import dev.codex.redindiansnight.Event.Application.Services.AnswerService;
import dev.codex.redindiansnight.Event.Application.Services.EventQuestionService;
import dev.codex.redindiansnight.Event.Domain.Entities.Answer;
import dev.codex.redindiansnight.Event.Domain.Entities.EventQuestion;
import dev.codex.redindiansnight.Event.Infrastructure.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;
    private final EventQuestionService eventQuestionService;

    @Override
    public List<Answer> findByEventQuestionId(Long eventQuestionId) {
        return repository.findByEventQuestionId(eventQuestionId);
    }

    @Override
    public Answer create(AnswerRequest request) {
        final EventQuestion eventQuestion = eventQuestionService.findById(request.eventQuestionId());

        final Answer answer = new Answer(request.answer(), eventQuestion);
        return repository.save(answer);
    }
}
