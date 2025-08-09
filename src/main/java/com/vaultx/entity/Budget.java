package com.vaultx.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "budgets")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Budget {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private Double limitAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne @JoinColumn(name = "user_id") private User user;
}
