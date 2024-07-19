package dev.codex.redindiansnight.Booking.Interfaces.Web;

import dev.codex.redindiansnight.Booking.Application.DTOs.Requests.BookingRequest;
import dev.codex.redindiansnight.Booking.Application.Services.BookingService;
import dev.codex.redindiansnight.Booking.Domain.Entities.Booking;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService service;
    private final HttpSession httpSession;

    @GetMapping
    public ResponseEntity<List<Booking>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Booking>> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByUserId(id));
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<List<Booking>> findByEventId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findByEventId(id));
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody @Valid BookingRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
