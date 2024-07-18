package dev.codex.redindiansnight.Blog.Application.Services.Impl;

import dev.codex.redindiansnight.Blog.Application.Dtos.Requests.BlogRequest;
import dev.codex.redindiansnight.Blog.Application.Services.BlogService;
import dev.codex.redindiansnight.Blog.Domain.Entities.Blog;
import dev.codex.redindiansnight.Blog.Domain.Exceptions.BlogNotFoundException;
import dev.codex.redindiansnight.Blog.Infrastructure.BlogRepository;
import dev.codex.redindiansnight.User.Application.Services.UserService;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class BlogServiceImpl implements BlogService {


    private final BlogRepository repository;
    private final UserService userService;
    @Override
    public List<Blog> findAll () {
        return repository.findAll();
    }

    @Override
    public Blog findById ( Long id ) {
        return repository.findById(id)
                .orElseThrow(() -> new BlogNotFoundException(id));
    }

    @Override
    public Blog create ( BlogRequest blogRequest ) {
        final User user = userService.findById(blogRequest.userId());
        final Blog blog = new Blog(blogRequest.title(),blogRequest.description(),blogRequest.content(),user);
        return repository.save(blog);
    }

    @Override
    public Blog update ( Long aLong, BlogRequest blogRequest ) {
        return null;
    }

    @Override
    public void delete ( Long aLong ) {

    }
}
