package dev.codex.redindiansnight.Booking.Application.DTOs.Requests;

import dev.codex.redindiansnight.Booking.Domain.ObjectValues.BookingStatus;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link dev.codex.redindiansnight.User.Domain.Entities.Permission}
 */
public record BookingRequest(
        @NotNull
        Integer userId,

        @NotNull
        Long eventId,

        @NotNull
        BookingStatus status
) implements Serializable {
}
