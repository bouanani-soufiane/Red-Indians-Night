package dev.codex.redindiansnight.Event.Domain.Entities;


import dev.codex.redindiansnight.Common.Models.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "event_questions")
public class EventQuestion extends AbstractEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Question question;

    public EventQuestion(Event event, Question question) {
        this.event = event;
        this.question = question;
    }
}
