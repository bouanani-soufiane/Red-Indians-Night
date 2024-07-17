package dev.codex.redindiansnight.Event.Interfaces.Web;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.AnswerRequest;
import dev.codex.redindiansnight.Event.Application.Services.AnswerService;
import dev.codex.redindiansnight.Event.Domain.Entities.Answer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService service;

    @GetMapping("/event-question/{eventQuestionId}")
    public ResponseEntity<List<Answer>> findByEventQuestionId(@PathVariable Long eventQuestionId) {
        List<Answer> answers = service.findByEventQuestionId(eventQuestionId);
        return ResponseEntity.ok(answers);
    }

    @PostMapping
    public ResponseEntity<Answer> create(@RequestBody @Valid AnswerRequest dto) {
        Answer createdAnswer = service.create(dto);
        return new ResponseEntity<>(createdAnswer, HttpStatus.CREATED);
    }
}