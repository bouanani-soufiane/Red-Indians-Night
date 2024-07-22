package dev.codex.redindiansnight.Booking.Application.Services.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.codex.redindiansnight.Booking.Application.DTOs.Requests.BookingRequest;
import dev.codex.redindiansnight.Booking.Application.Services.BookingService;
import dev.codex.redindiansnight.Booking.Domain.Entities.Booking;
import dev.codex.redindiansnight.Booking.Domain.Exceptions.BookingNotFoundException;
import dev.codex.redindiansnight.Booking.Domain.ObjectValues.BookingStatus;
import dev.codex.redindiansnight.Booking.Infrastructure.BookingRepository;
import dev.codex.redindiansnight.Event.Application.Services.EventService;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import dev.codex.redindiansnight.User.Application.Services.UserService;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final BookingRepository repository;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public List<Booking> findAll() {
        return repository.findAll();
    }

    @Override
    public Booking findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }

    @Override
    public List<Booking> findByUserId(Long id) {
       return repository.findByUserId(id);
    }

    @Override
    public List<Booking> findByEventId(Long id) {
        return repository.findByEventId(id);
    }

    @Override
    public Booking create(BookingRequest request) {
        final User user = userService.findById(request.userId());
        final Event event = eventService.findById(request.eventId());

        return repository.save(
                new Booking(user, event, request.status())
        );
    }

    @Override
   public void delete(Long id) {
        if (!repository.existsById(id))
            throw new BookingNotFoundException(id);
        repository.deleteById(id);
   }

   @Override
   public void changeStatus(Long id, BookingStatus status) {
       Booking booking = findById(id);
       booking.setStatus(status);
       repository.save(booking);
   }
}
