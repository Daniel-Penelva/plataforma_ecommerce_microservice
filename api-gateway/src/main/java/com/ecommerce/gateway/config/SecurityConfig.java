package com.ecommerce.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        // Endpoints públicos (sem autenticação)
                        .pathMatchers("/api/users/register").permitAll()
                        .pathMatchers("/api/products").permitAll()
                        .pathMatchers("/actuator/health").permitAll()
                        .pathMatchers("/fallback/**").permitAll()
                        // Todos os outros exigem JWT válido
                        .anyExchange().authenticated()
                )
                // Valida JWT com JWKS do Keycloak automaticamente
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> {
                        })
                )
                .build();
    }

}
