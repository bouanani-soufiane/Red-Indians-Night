package dev.codex.redindiansnight.Event.Application.Dtos.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.scheduling.support.SimpleTriggerContext;

public record AnswerRequest(
        @NotBlank
        String answer,
        @NotNull
        Long eventQuestionId
) {
}
