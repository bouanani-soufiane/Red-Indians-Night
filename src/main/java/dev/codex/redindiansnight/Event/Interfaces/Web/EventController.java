package dev.codex.redindiansnight.Event.Interfaces.Web;

import dev.codex.redindiansnight.Event.Application.Dtos.Requests.EventRequest;
import dev.codex.redindiansnight.Event.Application.Services.EventService;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService service;

    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        List<Event> events = service.findAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        Event event = service.findById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody @Valid EventRequest dto) {
        Event createdEvent = service.create(dto);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody @Valid EventRequest dto) {
        Event event = service.update(id, dto);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}