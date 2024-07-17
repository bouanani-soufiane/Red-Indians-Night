package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.AnswerRequest;
import dev.codex.redindiansnight.Event.Domain.Entities.Answer;

public interface AnswerService extends CrudService<Answer, Long, AnswerRequest> {
}
