package dev.codex.redindiansnight.Event.Application.Dtos.Requests;

import jakarta.validation.constraints.NotBlank;

public record AnswerRequest(
        @NotBlank
        String answer
) {
}
