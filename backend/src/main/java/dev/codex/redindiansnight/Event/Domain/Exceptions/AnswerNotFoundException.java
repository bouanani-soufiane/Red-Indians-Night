package dev.codex.redindiansnight.Event.Domain.Exceptions;

public class AnswerNotFoundException extends RuntimeException {
    private final Long id;

    public AnswerNotFoundException(Long id) {
        super("Answer not found with id: " + id);
        this.id = id;
    }
}
