package dev.codex.redindiansnight.User.Domain.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.codex.redindiansnight.Common.Models.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "hobby_details")
public class HobbyDetails extends AbstractEntity<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String text;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Hobby hobby;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserHobbies userHobbies;
}
