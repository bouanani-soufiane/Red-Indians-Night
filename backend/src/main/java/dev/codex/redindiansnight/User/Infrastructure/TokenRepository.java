package dev.codex.redindiansnight.User.Infrastructure;

import dev.codex.redindiansnight.User.Domain.Entities.Token;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.id = u.id\s
            where u.id = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(Integer id);

    void deleteAllByUser(User user);

    Optional<Token> findByToken(String token);
}
