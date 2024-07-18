package dev.codex.redindiansnight.Booking.Domain.Exceptions;

public class BookingNotFoundException extends RuntimeException {
    private final Long id;

    public BookingNotFoundException(Long id) {
        super("Booking with id " + id + " not found");
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
