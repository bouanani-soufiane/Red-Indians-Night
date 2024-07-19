package dev.codex.redindiansnight.Security;


import dev.codex.redindiansnight.User.Application.Services.Authentication.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
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

import static org.springframework.http.HttpMethod.*;

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

                                .requestMatchers(GET, "/api/v1/products/**").hasAnyAuthority("VIEW_PRODUCTS")
                                .requestMatchers(POST, "/api/v1/products/**").hasAnyAuthority("CREATE_PRODUCTS")
                                .requestMatchers(PUT, "/api/v1/products/**").hasAnyAuthority("UPDATE_PRODUCTS")
                                .requestMatchers(DELETE, "/api/v1/products/**").hasAnyAuthority("DELETE_PRODUCTS")

                                .requestMatchers(GET, "/api/v1/roles/**").hasAnyAuthority("VIEW_ROLES")
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

                                .requestMatchers(GET, "/api/v1/countries/**").hasAnyAuthority("VIEW_COUNTRIES")
                                .requestMatchers(POST, "/api/v1/countries/**").hasAnyAuthority("CREATE_COUNTRIES")
                                .requestMatchers(PUT, "/api/v1/countries/**").hasAnyAuthority("UPDATE_COUNTRIES")
                                .requestMatchers(DELETE, "/api/v1/countries/**").hasAnyAuthority("DELETE_COUNTRIES")

                                .requestMatchers(GET, "/api/v1/cities/**").hasAnyAuthority("VIEW_CITIES")
                                .requestMatchers(POST, "/api/v1/cities/**").hasAnyAuthority("CREATE_CITIES")
                                .requestMatchers(PUT, "/api/v1/cities/**").hasAnyAuthority("UPDATE_CITIES")
                                .requestMatchers(DELETE, "/api/v1/cities/**").hasAnyAuthority("DELETE_CITIES")

                                .requestMatchers(GET, "/api/v1/partners/**").hasAnyAuthority("VIEW_PARTNERS")
                                .requestMatchers(POST, "/api/v1/partners/**").hasAnyAuthority("CREATE_PARTNERS")
                                .requestMatchers(PUT, "/api/v1/partners/**").hasAnyAuthority("UPDATE_PARTNERS")
                                .requestMatchers(DELETE, "/api/v1/partners/**").hasAnyAuthority("DELETE_PARTNERS")

                                .requestMatchers(GET, "/api/v1/platforms/**").hasAnyAuthority("VIEW_PLATFORMS")

                                .requestMatchers(POST, "/api/v1/orders/**").hasAnyAuthority("CREATE_ORDERS")
                                .requestMatchers(PUT, "/api/v1/orders/**").hasAnyAuthority("UPDATE_ORDERS")
                                .requestMatchers(DELETE, "/api/v1/orders/**").hasAnyAuthority("DELETE_ORDERS")
                                .requestMatchers(GET, "/api/v1/orders/**").hasAnyAuthority("VIEW_ORDERS")

                                .requestMatchers(GET, "/api/v1/carts/**").hasAnyAuthority("VIEW_CARTS")
                                .requestMatchers(POST, "/api/v1/carts/**").hasAnyAuthority("CREATE_CARTS")
                                .requestMatchers(PUT, "/api/v1/carts/**").hasAnyAuthority("UPDATE_CARTS")
                                .requestMatchers(DELETE, "/api/v1/carts/**").hasAnyAuthority("DELETE_CARTS")

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
