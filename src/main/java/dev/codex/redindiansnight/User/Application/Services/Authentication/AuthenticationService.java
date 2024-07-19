package dev.codex.redindiansnight.User.Application.Services.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication.AuthenticationRequest;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication.AuthenticationResponse;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.Authentication.RegisterRequest;
import dev.codex.redindiansnight.User.Application.DTOs.Requests.UserRequest;
import dev.codex.redindiansnight.User.Application.Services.RoleService;
import dev.codex.redindiansnight.User.Application.Services.UserService;
import dev.codex.redindiansnight.User.Domain.Entities.Token;
import dev.codex.redindiansnight.User.Domain.Entities.User;
import dev.codex.redindiansnight.User.Domain.ValueObjects.TokenType;
import dev.codex.redindiansnight.User.Infrastructure.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            User user = userService.create(request);

            String jwtToken = jwtService.generateToken(user, user);
            String refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(user, jwtToken);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK6dotkott2kjsp8vw4d0m25fb7")) {
                throw new DuplicateEmailException("A user with the email " + request.email() + " already exists.");
            }
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException(e);
        }
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userService.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user, user);
        String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder().accessToken(jwtToken).refreshToken(refreshToken).build();
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User user = userService.findByEmail(userEmail);
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user, user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                AuthenticationResponse authResponse = AuthenticationResponse.builder().accessToken(accessToken).refreshToken(refreshToken).build();
                response.getWriter().write(new ObjectMapper().writeValueAsString(authResponse));
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        tokenRepository.save(Token.builder().user(user).token(jwtToken).tokenType(TokenType.BEARER).expired(false).revoked(false).build());
    }

    private void revokeAllUserTokens(User user) {
        tokenRepository.findAllValidTokenByUser(user.getId()).forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
    }
}
