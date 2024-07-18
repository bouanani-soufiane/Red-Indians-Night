package dev.codex.redindiansnight.Event.Application.Dtos.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerRequest(
        @NotBlank
        String answer,
        @NotNull
        Long eventQuestionId
) {
}
