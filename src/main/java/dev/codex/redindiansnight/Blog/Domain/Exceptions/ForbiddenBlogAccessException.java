package dev.codex.redindiansnight.Blog.Domain.Exceptions;

public class ForbiddenBlogAccessException extends RuntimeException{
    public ForbiddenBlogAccessException(String message) {
        super(message);
    }
}
