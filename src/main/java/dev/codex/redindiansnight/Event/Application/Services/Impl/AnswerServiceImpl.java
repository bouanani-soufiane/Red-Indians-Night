package dev.codex.redindiansnight.Event.Application.Services.Impl;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.AnswerRequest;
import dev.codex.redindiansnight.Event.Application.Services.AnswerService;
import dev.codex.redindiansnight.Event.Domain.Entities.Answer;
import dev.codex.redindiansnight.Event.Domain.Exceptions.AnswerNotFoundException;
import dev.codex.redindiansnight.Event.Infrastructure.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository repository;

    @Override
    public List<Answer> findAll() {
        return repository.findAll();
    }

    @Override
    public Answer findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AnswerNotFoundException(id));
    }

    @Override
    public Answer create(AnswerRequest answerRequest) {
        return null;
    }

    @Override
    public Answer update(Long aLong, AnswerRequest answerRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
