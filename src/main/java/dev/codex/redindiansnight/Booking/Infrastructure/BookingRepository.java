package dev.codex.redindiansnight.Booking.Infrastructure;

import dev.codex.redindiansnight.Booking.Domain.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserId(Long id);
    List<Booking> findByEventId(Long id);
}