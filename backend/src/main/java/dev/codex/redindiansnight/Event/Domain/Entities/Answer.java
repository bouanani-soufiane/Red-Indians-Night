package dev.codex.redindiansnight.Event.Domain.Entities;

import dev.codex.redindiansnight.Common.Models.AbstractEntity;
import dev.codex.redindiansnight.Event.Application.Dtos.Requests.AnswerRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "answers")
public class Answer extends AbstractEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    private String answer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "event_question_id")
    private EventQuestion eventQuestion;

    public Answer (String answer, EventQuestion eventQuestion) {
        this.answer = answer;
        this.eventQuestion = eventQuestion;
    }
}
