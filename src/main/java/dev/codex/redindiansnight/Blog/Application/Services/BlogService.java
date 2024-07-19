package dev.codex.redindiansnight.Blog.Application.Services;
import dev.codex.redindiansnight.Blog.Application.Dtos.Requests.BlogRequest;
import dev.codex.redindiansnight.Blog.Domain.Entities.Blog;
import dev.codex.redindiansnight.Common.Contracts.CrudService;

public interface BlogService extends CrudService<Blog, Long, BlogRequest>{

}
