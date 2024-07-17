package dev.codex.redindiansnight.Event.Domain.Exceptions;

public class QuestionNotFoundNotFoundException extends RuntimeException {
    private final Long id;

    public QuestionNotFoundNotFoundException(Long id) {
        super("question" +
                " not found with id: " + id);
        this.id = id;
    }
}
