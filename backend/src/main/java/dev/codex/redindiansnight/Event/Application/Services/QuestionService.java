package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.QuestionRequest;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;

import java.util.Collection;
import java.util.List;

public interface QuestionService extends CrudService<Question, Long, QuestionRequest> {
    List<Question> findAllById(List<Long> ids);

    List<Question> createAll(List<QuestionRequest> questionRequests);
}
