package com.vaultx.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vaultx.repository.UserRepository;
import com.vaultx.repository.RefreshTokenRepository;
import com.vaultx.entity.User;
import com.vaultx.entity.RefreshToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.vaultx.security.JwtUtil;
import com.vaultx.dto.AuthRequest;
import com.vaultx.dto.AuthResponse;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse signup(AuthRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) throw new RuntimeException("Email exists");
        User u = User.builder().email(req.getEmail()).name(req.getName()).password(passwordEncoder.encode(req.getPassword())).role("ROLE_USER").build();
        userRepository.save(u);
        String access = jwtUtil.generateToken(u.getId(), u.getRole());
        String refresh = createRefreshToken(u.getId());
        return new AuthResponse(access, refresh);
    }

    public AuthResponse login(AuthRequest req) {
        User u = userRepository.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(req.getPassword(), u.getPassword())) throw new RuntimeException("Invalid credentials");
        String access = jwtUtil.generateToken(u.getId(), u.getRole());
        String refresh = createRefreshToken(u.getId());
        return new AuthResponse(access, refresh);
    }

    private String createRefreshToken(Long userId) {
        String token = UUID.randomUUID().toString();
        RefreshToken rt = RefreshToken.builder().token(token).userId(userId).expiryDate(Instant.now().plusMillis(604800000L)).build();
        refreshTokenRepository.save(rt);
        return token;
    }

    public String refreshAccessToken(String refreshToken) {
        RefreshToken rt = refreshTokenRepository.findByToken(refreshToken).orElseThrow(() -> new RuntimeException("Invalid refresh token"));
        if (rt.getExpiryDate().isBefore(Instant.now())) throw new RuntimeException("Expired refresh token");
        User u = userRepository.findById(rt.getUserId()).orElseThrow();
        return jwtUtil.generateToken(u.getId(), u.getRole());
    }
}
