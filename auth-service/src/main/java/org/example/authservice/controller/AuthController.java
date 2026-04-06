package org.example.authservice.controller;

import org.example.authservice.dto.LoginRequest;
import org.example.authservice.dto.SignupRequest;
import org.example.authservice.model.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtil jwtUtil;
    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));
            if (auth.isAuthenticated()) {
                return ResponseEntity.ok(jwtUtil.generateToken(request.getUsername()));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        // Prefer explicit username, fall back to email
        String username = (request.getUsername() != null && !request.getUsername().isBlank())
                ? request.getUsername() : request.getEmail();

        if (username == null || username.isBlank())
            return ResponseEntity.badRequest().body("Username or email is required");
        if (request.getPassword() == null || request.getPassword().isBlank())
            return ResponseEntity.badRequest().body("Password is required");
        if (userRepository.findByUsername(username).isPresent())
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Username already exists: " + username);

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");
        userRepository.save(user);

        // Auto-login: return JWT immediately
        String token = jwtUtil.generateToken(username);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("token", token, "username", username));
    }

    @GetMapping("/google-success")
    public ResponseEntity<?> googleLoginSuccess(Authentication authentication) {
        if (authentication == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not authenticated");
        return ResponseEntity.ok(jwtUtil.generateToken(authentication.getName()));
    }
}