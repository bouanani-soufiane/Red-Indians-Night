package dev.codex.redindiansnight.Blog.Application.Dtos.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequest(
        @NotBlank
        String text,
        @NotNull
        Long userId,
        @NotNull
        Long blogId
) {
}
