package dev.codex.redindiansnight.User.Infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.codex.redindiansnight.Common.Seeder.Seeder;
import dev.codex.redindiansnight.User.Domain.Entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder extends Seeder<Role, RoleRepository> {
    public RoleSeeder(RoleRepository repository, ObjectMapper objectMapper) {
        super(repository, "roles", objectMapper, Role.class);
    }
}
