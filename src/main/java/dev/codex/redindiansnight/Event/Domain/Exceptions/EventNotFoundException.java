package dev.codex.redindiansnight.Event.Domain.Exceptions;

public class EventNotFoundException extends RuntimeException {
    private final Long id;

    public EventNotFoundException(Long id) {
        super("event not found with id: " + id);
        this.id = id;
    }
}
