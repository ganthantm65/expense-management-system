package com.expense.ExpenseManagement.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseRequest {
    @NotNull
    private String category;

    private BigDecimal amount;

    @NotNull
    private String description;

    @NotNull
    private LocalDate expenseDate;
}
