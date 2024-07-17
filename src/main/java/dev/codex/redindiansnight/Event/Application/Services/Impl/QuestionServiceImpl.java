package dev.codex.redindiansnight.Event.Application.Services.Impl;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.QuestionRequest;
import dev.codex.redindiansnight.Event.Application.Services.QuestionService;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;
import dev.codex.redindiansnight.Event.Domain.Exceptions.QuestionNotFoundNotFoundException;
import dev.codex.redindiansnight.Event.Infrastructure.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository repository;

    @Override
    public List<Question> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Question> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @Transactional
    public List<Question> createAll(List<QuestionRequest> questionRequests) {
        final List<Question> questions = questionRequests.stream()
                .map(questionRequest -> new Question(
                        questionRequest.question(),
                        questionRequest.isRequired(),
                        questionRequest.isAutoGenerated(),
                        questionRequest.answerType()
                ))
                .toList();

        return repository.saveAll(questions);
    }

    @Override
    public Question findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundNotFoundException(id));
    }

    @Override
    @Transactional
    public Question create(QuestionRequest request) {
        final Question question = new Question(
                request.question(),
                request.isRequired(),
                request.isAutoGenerated(),
                request.answerType()
        );
        return repository.save(question);
    }

    @Override
    @Transactional
    public Question update(Long id, QuestionRequest questionRequest) {
        final Question question = repository.findById(id)
                .orElseThrow(() -> new QuestionNotFoundNotFoundException(id));
        question.setQuestion(questionRequest.question());
        question.setIsRequired(questionRequest.isRequired());
        question.setIsAutoGenerated(questionRequest.isAutoGenerated());
        question.setAnswerType(questionRequest.answerType());
        return repository.save(question);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (repository.existsById(id))
            throw new QuestionNotFoundNotFoundException(id);
        repository.deleteById(id);
    }
}
