package dev.codex.redindiansnight.Event.Domain.Entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ManyToAny;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "event_questions")
public class EventQuestion {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Question question;
}
