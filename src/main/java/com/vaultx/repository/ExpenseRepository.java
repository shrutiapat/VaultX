package com.vaultx.repository;

import com.vaultx.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUserIdAndSpentAtBetween(Long userId, LocalDate start, LocalDate end);
    List<Expense> findByUserId(Long userId);
}
