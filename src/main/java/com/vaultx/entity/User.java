package com.vaultx.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String email;
    private String name;
    private String phone;
    private String password;
    private String role; // ROLE_USER or ROLE_ADMIN
}
