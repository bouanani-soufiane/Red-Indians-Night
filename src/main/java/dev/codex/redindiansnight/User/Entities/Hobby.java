package dev.codex.redindiansnight.User.Entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.aop.aspectj.AspectJWeaverMessageHandler;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "hobbies")
public class Hobby {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JsonIgnore
    private List<User> users;

    @OneToMany(mappedBy = "hobby", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HobbyDetails> hobbyDetails;
}
