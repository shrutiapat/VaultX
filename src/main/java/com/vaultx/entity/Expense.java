package com.vaultx.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate spentAt;
    private String paymentMode;
    private String upiId;
    private String transactionRef;
    private String provider;
    private String subscriptionName;
    private LocalDate renewalDate;
    private Boolean gstClaimable = false;
    private String gstNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
