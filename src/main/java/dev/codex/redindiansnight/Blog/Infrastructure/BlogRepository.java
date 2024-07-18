package dev.codex.redindiansnight.Blog.Infrastructure;

import dev.codex.redindiansnight.Blog.Domain.Entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository extends JpaRepository<Blog, Long> {

}
