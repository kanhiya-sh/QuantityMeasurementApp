package org.example.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * CORS configuration — reads allowed frontend URL from FRONTEND_URL env var.
 *
 * In Render dashboard, set:
 *   FRONTEND_URL = https://your-frontend.onrender.com
 *
 * Both localhost:4200 (local dev) and the production Render URL are allowed.
 * setAllowCredentials(true) is needed for the Authorization header to work.
 */
@Configuration
public class CorsConfig {

    @Value("${app.frontend-url:http://localhost:4200}")
    private String frontendUrl;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow local dev AND production frontend
        config.setAllowedOrigins(List.of(
            "http://localhost:4200",
            frontendUrl
        ));

        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
