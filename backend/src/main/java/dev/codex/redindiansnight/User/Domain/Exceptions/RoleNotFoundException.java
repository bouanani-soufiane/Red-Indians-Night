package dev.codex.redindiansnight.User.Domain.Exceptions;

public class RoleNotFoundException extends RuntimeException {
    private final Long id;
    private final String name;

    public RoleNotFoundException(Long id) {
        super("Role with id " + id + " not found");
        this.id = id;
        this.name = null;
    }

    public RoleNotFoundException(String name) {
        super("Role with name " + name + " not found");
        this.id = null;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

