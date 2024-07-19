package dev.codex.redindiansnight.User.Application.Services.Authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.foodeals.pro_market.Modules.Authentication.Dtos.AuthenticationRequest;
import net.foodeals.pro_market.Modules.Authentication.Dtos.AuthenticationResponse;
import net.foodeals.pro_market.Modules.Authentication.Dtos.RegisterRequest;
import net.foodeals.pro_market.Modules.Role.Role;
import net.foodeals.pro_market.Modules.Role.RoleRepository;
import net.foodeals.pro_market.Modules.Token.Token;
import net.foodeals.pro_market.Modules.Token.TokenRepository;
import net.foodeals.pro_market.Modules.Token.TokenType;
import net.foodeals.pro_market.Modules.User.User;
import net.foodeals.pro_market.Modules.User.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        try {
            String date = java.time.LocalDate.now().toString();
            Role role = roleRepository.findById(request.roleId())
                    .orElseThrow(() -> new NoSuchElementException("Role not found for ID: " + request.roleId()));

            User user = User.builder()
                    .email(request.email())
                    .password(passwordEncoder.encode(request.password()))
                    .name(request.name())
                    .role(role)
                    .createdAt(date)
                    .build();

            userRepository.save(user);

            String jwtToken = jwtService.generateToken(user, user);
            String refreshToken = jwtService.generateRefreshToken(user);
            saveUserToken(user, jwtToken);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("UK6dotkott2kjsp8vw4d0m25fb7")) { // Adjust to match your actual unique constraint name
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
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("the user not found here ! "));
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
            User user = this.userRepository.findByEmail(userEmail).orElseThrow();
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
