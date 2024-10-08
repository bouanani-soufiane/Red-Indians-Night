package dev.codex.redindiansnight.User.Domain.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.codex.redindiansnight.Common.Models.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "permissions")
public class Permission extends AbstractEntity<Long> {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<User> users;

    public Permission(String name) {
        this.name = name;
    }
}
