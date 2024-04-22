package com.miguel.cloudgateway.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter authenticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity) {
        return httpSecurity.authorizeExchange(auth -> auth
                        .pathMatchers(HttpMethod.POST, "/api/client/**").hasAnyRole("POST")
                        .pathMatchers(HttpMethod.PATCH, "/api/client/**").hasAnyRole("PUT", "PATCH")
                        .pathMatchers(HttpMethod.GET, "/api/client-query/**").hasAnyRole("GET")
                        .pathMatchers(HttpMethod.POST, "/api/license/**").hasAnyRole("POST")
                        .pathMatchers(HttpMethod.PATCH, "/api/license/**").hasAnyRole("PUT", "PATCH")
                        .pathMatchers(HttpMethod.GET, "/api/license-query/**").hasAnyRole("GET")
                        .pathMatchers(HttpMethod.POST, "/api/user/**").hasAnyRole("POST")
                        .pathMatchers("/api/user-query/**").permitAll()
                        .anyExchange().permitAll()
                )
                .addFilterAfter(authenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .build();
    }
}
