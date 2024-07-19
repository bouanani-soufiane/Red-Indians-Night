package dev.codex.redindiansnight.Blog.Infrastructure;

import dev.codex.redindiansnight.Blog.Domain.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
