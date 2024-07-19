package dev.codex.redindiansnight.Booking.Domain.Entities;

import dev.codex.redindiansnight.Booking.Domain.ObjectValues.BookingStatus;
import dev.codex.redindiansnight.Event.Domain.Entities.Event;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Event event;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    public Booking(User user, Event event, BookingStatus status) {
        this.user = user;
        this.event = event;
        this.status = status;
    }
}