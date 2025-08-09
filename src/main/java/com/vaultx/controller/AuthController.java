package com.vaultx.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.vaultx.service.AuthService;
import com.vaultx.dto.AuthRequest;
import com.vaultx.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.signup(req));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestBody String refreshToken) {
        String access = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(access);
    }
}
