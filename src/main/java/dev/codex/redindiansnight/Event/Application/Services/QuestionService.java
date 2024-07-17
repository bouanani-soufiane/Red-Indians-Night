package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.QuestionRequest;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;

public interface QuestionService extends CrudService<Question, Long, QuestionRequest> {
}
