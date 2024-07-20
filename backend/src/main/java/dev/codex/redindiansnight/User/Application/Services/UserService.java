package dev.codex.redindiansnight.User.Application.Services;

import dev.codex.redindiansnight.Common.Contracts.CrudService;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication.RegisterRequest;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.UserRequest;
import dev.codex.redindiansnight.User.Domain.Entities.User;

public interface UserService extends CrudService<User, Integer, UserRequest> {
    User findByEmail(String email);

    User create(RegisterRequest request);
}
