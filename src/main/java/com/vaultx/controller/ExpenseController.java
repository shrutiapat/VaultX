package com.vaultx.controller;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.vaultx.service.ExpenseService;
import com.vaultx.dto.ExpenseDTO;
import com.vaultx.entity.Expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")

public class ExpenseController {
    private final ExpenseService expenseService;
     @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Expense> addExpense(Authentication auth, @RequestBody ExpenseDTO dto) {
        String email = auth.getName();
        return ResponseEntity.ok(expenseService.addExpense(email, dto));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> list(@RequestParam(required=false) String filter,
                                              @RequestParam(required=false) String start,
                                              @RequestParam(required=false) String end,
                                              Authentication auth) {
        String email = auth.getName();
        LocalDate s = start == null ? null : LocalDate.parse(start);
        LocalDate e = end == null ? null : LocalDate.parse(end);
        if ("week".equalsIgnoreCase(filter)) { e = LocalDate.now(); s = e.minusWeeks(1); }
        if ("month".equalsIgnoreCase(filter)) { e = LocalDate.now(); s = e.minusMonths(1); }
        if ("3months".equalsIgnoreCase(filter)) { e = LocalDate.now(); s = e.minusMonths(3); }
        return ResponseEntity.ok(expenseService.listExpensesForUser(email, s, e));
    }
}
