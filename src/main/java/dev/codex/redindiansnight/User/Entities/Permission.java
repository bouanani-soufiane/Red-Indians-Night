package dev.codex.redindiansnight.User.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.LifecycleState;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany
    @JsonIgnore
    private List<User> users;
}
