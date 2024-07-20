package dev.codex.redindiansnight.Blog.Domain.Exceptions;

public class BlogNotFoundException  extends RuntimeException {
    private final Long id;

    public BlogNotFoundException(Long id) {
        super("blog" + " not found with id: " + id);
        this.id = id;
    }
}
