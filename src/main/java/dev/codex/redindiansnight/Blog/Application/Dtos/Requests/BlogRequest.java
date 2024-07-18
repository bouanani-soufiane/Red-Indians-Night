package dev.codex.redindiansnight.Blog.Application.Dtos.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


public record BlogRequest(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotBlank
        String content,

        @NotNull
        Long userId
) implements Serializable {
}
