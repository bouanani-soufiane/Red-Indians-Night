package dev.codex.redindiansnight.User.Domain.Exceptions;

public class UserNotFoundException extends RuntimeException {
    private final Integer id;
    private final String email;

    public UserNotFoundException(Integer id) {
        super("User with id " + id + " not found");
        this.id = id;
        this.email = null;
    }

    public UserNotFoundException(String email) {
        super("User with email " + email + " not found");
        this.email = email;
        this.id = null;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
