package dev.codex.redindiansnight.Event.Domain.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue
    private Long id;

    private String answer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_question_id")
    private EventQuestion eventQuestion;
}
