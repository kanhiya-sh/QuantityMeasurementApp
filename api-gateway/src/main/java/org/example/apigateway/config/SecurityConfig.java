package org.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                // ensure CORS is processed before Spring Security
                .cors(cors -> {})   // enable CORS
                .csrf(csrf -> csrf.disable())

                .authorizeExchange(exchange -> exchange
                        // allow preflight requests
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange().permitAll()
                )
                .build();
    }
}