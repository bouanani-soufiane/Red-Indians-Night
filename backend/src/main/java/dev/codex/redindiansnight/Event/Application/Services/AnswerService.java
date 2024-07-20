package dev.codex.redindiansnight.Event.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.AnswerRequest;
import dev.codex.redindiansnight.Event.Domain.Entities.Answer;

import java.util.List;

public interface AnswerService {
    List<Answer> findByEventQuestionId(Long eventQuestionId);
    Answer create(AnswerRequest request);
}
