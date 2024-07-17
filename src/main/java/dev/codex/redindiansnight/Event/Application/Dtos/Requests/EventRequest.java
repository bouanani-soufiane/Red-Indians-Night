package dev.codex.redindiansnight.Event.Application.Dtos.Requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record EventRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        Long price,
        @NotBlank
        String location,
        @NotNull
        Date startDate,
        @NotNull
        Date endDate,
        @NotNull
        Long numberOfAttendees,
        @NotNull
        Boolean isLive
) {
}
