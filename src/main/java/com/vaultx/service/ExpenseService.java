package com.vaultx.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.vaultx.repository.ExpenseRepository;
import com.vaultx.repository.UserRepository;
import com.vaultx.entity.Expense;
import com.vaultx.entity.User;
import com.vaultx.dto.ExpenseDTO;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public Expense addExpense(String email, ExpenseDTO dto) {
        User u = userRepository.findByEmail(email).orElseThrow();
        Expense e = Expense.builder()
                .title(dto.getTitle())
                .amount(dto.getAmount())
                .paymentMode(dto.getPaymentMode())
                .upiId(dto.getUpiId())
                .transactionRef(dto.getTransactionRef())
                .provider(dto.getProvider())
                .subscriptionName(dto.getSubscriptionName())
                .renewalDate(dto.getRenewalDate())
                .spentAt(dto.getSpentAt() == null ? LocalDate.now() : dto.getSpentAt())
                .user(u)
                .build();
        e.setCategory(autoCategorize(e));
        return expenseRepository.save(e);
    }

    private String autoCategorize(Expense e) {
        String t = e.getTitle() == null ? "" : e.getTitle().toLowerCase();
        if (e.getSubscriptionName() != null && !e.getSubscriptionName().isBlank()) return "OTT_SUBSCRIPTION";
        if (t.contains("grocery") || t.contains("bigbasket") || t.contains("supermarket")) return "GROCERIES";
        if (t.contains("swiggy") || t.contains("zomato")) return "FOOD"; 
        if ("UPI".equalsIgnoreCase(e.getPaymentMode())) return "UPI_TRANSFER";
        return "OTHERS";
    }

    public List<Expense> listExpensesForUser(String email, LocalDate start, LocalDate end) {
        User u = userRepository.findByEmail(email).orElseThrow();
        if (start==null || end==null) return expenseRepository.findByUserId(u.getId());
        return expenseRepository.findByUserIdAndSpentAtBetween(u.getId(), start, end);
    }
}
