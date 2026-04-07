package org.example.apigateway.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * FIX 1: Implement Ordered with LOWEST_PRECEDENCE so CORS filter runs BEFORE this.
 *        Without this, JwtFilter intercepts OPTIONS preflight requests and returns
 *        403/401 before CORS headers are added — browser reports "Invalid CORS request".
 *
 * FIX 2: Explicitly allow ALL OPTIONS (preflight) requests through without JWT check.
 *        Browsers send OPTIONS before every cross-origin POST/GET with custom headers.
 *        The actual request follows only if OPTIONS succeeds with correct CORS headers.
 */
@Component
public class JwtFilter implements GlobalFilter, Ordered {

    @Autowired
    private JwtUtil jwtUtil;

    // Paths accessible without a JWT token
    private static final List<String> PUBLIC_PATHS = List.of(
            "/api/v1/auth",               // login, signup
            "/api/v1/quantities/compare"  // compare is open for guests
    );

    @Override
    public int getOrder() {
        // Run AFTER the built-in CORS WebFilter (which has order = -2)
        // This ensures CORS headers are set before our JWT check
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // FIX: Always pass OPTIONS preflight requests through — no JWT check needed.
        // The browser sends OPTIONS first for any cross-origin request with
        // Authorization header. If OPTIONS is blocked, the real request never fires.
        if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
            return chain.filter(exchange);
        }

        String path = exchange.getRequest().getURI().getPath();

        // Allow public paths through without JWT
        boolean isPublic = PUBLIC_PATHS.stream().anyMatch(path::startsWith);
        if (isPublic) {
            return chain.filter(exchange);
        }

        // Protected path — require valid Bearer token
        String header = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = header.substring(7);
        try {
            jwtUtil.extractUsername(token);
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }
}
