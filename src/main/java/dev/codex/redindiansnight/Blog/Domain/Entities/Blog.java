package dev.codex.redindiansnight.Blog.Domain.Entities;

import dev.codex.redindiansnight.User.Domain.Entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @OneToMany(mappedBy = "blog", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> commentList;




}
