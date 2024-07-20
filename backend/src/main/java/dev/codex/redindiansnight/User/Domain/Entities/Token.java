package dev.codex.redindiansnight.User.Domain.Entities;


import dev.codex.redindiansnight.Common.Models.AbstractEntity;
import dev.codex.redindiansnight.User.Domain.ValueObjects.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token extends AbstractEntity<Integer> {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true, length = 512)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    public User user;
}
