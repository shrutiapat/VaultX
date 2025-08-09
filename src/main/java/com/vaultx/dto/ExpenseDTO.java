package com.vaultx.dto;
import lombok.Data;
import java.time.LocalDate;
@Data
public class ExpenseDTO {
    private String title; private Double amount; private String paymentMode; private String upiId;
    private String transactionRef; private String provider; private String subscriptionName;
    private LocalDate renewalDate; private LocalDate spentAt;
}
