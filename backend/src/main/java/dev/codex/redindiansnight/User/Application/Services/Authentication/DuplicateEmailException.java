package dev.codex.redindiansnight.User.Application.Services.Authentication;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
