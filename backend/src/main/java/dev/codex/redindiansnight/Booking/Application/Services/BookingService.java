package dev.codex.redindiansnight.Booking.Application.Services;

import dev.codex.redindiansnight.Booking.Application.DTOs.Requests.BookingRequest;
import dev.codex.redindiansnight.Booking.Domain.Entities.Booking;
import dev.codex.redindiansnight.Booking.Domain.ObjectValues.BookingStatus;

import java.util.List;

public interface BookingService {
    List<Booking> findAll();

    Booking findById(Long id);

    List<Booking> findByUserId(Long id);

    List<Booking> findByEventId(Long id);

    Booking create(BookingRequest request);

    void delete(Long id);

    void changeStatus(Long id, BookingStatus status);
}
