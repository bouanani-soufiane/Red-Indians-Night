package dev.codex.redindiansnight.Blog.Domain.Entities;

import dev.codex.redindiansnight.User.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String description;
    private String content;

    @ManyToOne(fetch = FetchType.EAGER,  cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User author;

}
