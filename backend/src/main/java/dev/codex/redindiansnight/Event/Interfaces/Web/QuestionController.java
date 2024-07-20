package dev.codex.redindiansnight.Event.Interfaces.Web;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.QuestionRequest;
import dev.codex.redindiansnight.Event.Application.Services.QuestionService;
import dev.codex.redindiansnight.Event.Domain.Entities.Question;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService service;

    @GetMapping
    public ResponseEntity<List<Question>> findAll() {
        List<Question> questions = service.findAll();
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> findById(@PathVariable Long id) {
        Question question = service.findById(id);
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<Question> create(@RequestBody @Valid QuestionRequest dto) {
        Question createdQuestion = service.create(dto);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> update(@PathVariable Long id, @RequestBody @Valid QuestionRequest dto) {
        Question question = service.update(id, dto);
        return ResponseEntity.ok(question);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}