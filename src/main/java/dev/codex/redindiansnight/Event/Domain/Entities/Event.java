package dev.codex.redindiansnight.Event.Domain.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    private String price;

    private String location;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "number_of_attendees")
    private Long numberOfAttendees;

    @Column(name = "is_live")
    private Boolean isLive = false;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "events")
    private List<Question> questions;
}
