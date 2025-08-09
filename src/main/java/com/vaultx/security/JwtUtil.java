package com.vaultx.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}") private String jwtSecret;
    @Value("${jwt.expiration}") private Long jwtExpirationMs;

    public String generateToken(Long userId, String role) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        Date now = new Date();
        Date exp = new Date(now.getTime() + jwtExpirationMs);
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        Claims c = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return Long.parseLong(c.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }
}
