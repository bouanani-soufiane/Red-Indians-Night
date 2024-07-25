package dev.codex.redindiansnight.Security;


import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import dev.codex.redindiansnight.User.Application.Services.Authentication.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URLS = {
            "/api/v1/auth/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers(WHITE_LIST_URLS)
                                .permitAll()
                                .requestMatchers(GET, "/api/v1/roles/**")
                                .permitAll()
                                .requestMatchers(GET, "/api/v1/users/**").hasAnyAuthority("VIEW_USERS")
                                .requestMatchers(POST, "/api/v1/users/**").hasAnyAuthority("CREATE_USERS")
                                .requestMatchers(PUT, "/api/v1/users/**").hasAnyAuthority("UPDATE_USERS")
                                .requestMatchers(DELETE, "/api/v1/users/**").hasAnyAuthority("DELETE_USERS")

                                .requestMatchers(POST, "/api/v1/roles/**").hasAnyAuthority("CREATE_ROLES")
                                .requestMatchers(PUT, "/api/v1/roles/**").hasAnyAuthority("UPDATE_ROLES")
                                .requestMatchers(DELETE, "/api/v1/roles/**").hasAnyAuthority("DELETE_ROLES")

                                .requestMatchers(GET, "/api/v1/permissions/**").hasAnyAuthority("VIEW_PERMISSIONS")
                                .requestMatchers(POST, "/api/v1/permissions/**").hasAnyAuthority("CREATE_PERMISSIONS")
                                .requestMatchers(PUT, "/api/v1/permissions/**").hasAnyAuthority("UPDATE_PERMISSIONS")
                                .requestMatchers(DELETE, "/api/v1/permissions/**").hasAnyAuthority("DELETE_PERMISSIONS")

                                .requestMatchers(GET, "/api/v1/categories/**").hasAnyAuthority("VIEW_CATEGORIES")
                                .requestMatchers(POST, "/api/v1/categories/**").hasAnyAuthority("CREATE_CATEGORIES")
                                .requestMatchers(PUT, "/api/v1/categories/**").hasAnyAuthority("UPDATE_CATEGORIES")
                                .requestMatchers(DELETE, "/api/v1/categories/**").hasAnyAuthority("DELETE_CATEGORIES")
                                .anyRequest()
                                .authenticated()

                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout
                                .logoutUrl("/api/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );

        return http.build();
    }
}
