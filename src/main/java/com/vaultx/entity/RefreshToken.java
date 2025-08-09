package com.vaultx.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RefreshToken {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) private String token;
    private Long userId;
    private Instant expiryDate;
}
