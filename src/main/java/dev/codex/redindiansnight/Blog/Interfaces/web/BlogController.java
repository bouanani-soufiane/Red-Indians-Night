package dev.codex.redindiansnight.Blog.Interfaces.web;

import dev.codex.redindiansnight.Blog.Application.Dtos.Requests.BlogRequest;
import dev.codex.redindiansnight.Blog.Application.Services.BlogService;
import dev.codex.redindiansnight.Blog.Domain.Entities.Blog;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.UserRequest;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @GetMapping
    public ResponseEntity<List<Blog>> findAll() {
        List<Blog> blogs = blogService.findAll();
        return ResponseEntity.ok(blogs);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Blog> findById( @PathVariable Long id) {
        final Blog blog = blogService.findById(id);
        return ResponseEntity.ok(blog);
    }
    @PostMapping
    public ResponseEntity<Blog> create(@RequestBody @Valid BlogRequest dto) {
        final Blog createdBlog = blogService.create(dto);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> update(@PathVariable Long id, @RequestBody @Valid BlogRequest dto) {
        final Blog updatedBlog = blogService.update(id, dto);
        return ResponseEntity.ok(updatedBlog);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        blogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
