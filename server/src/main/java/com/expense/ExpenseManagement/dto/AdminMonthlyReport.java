package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AdminMonthlyReport {

    private Integer month;
    private Integer year;

    private BigDecimal totalExpense;

    private Long approved;
    private Long rejected;
    private Long pending;

    private BigDecimal totalGST;
    private BigDecimal totalTDS;

    private Double budget;
    private Double spent;
    private Double remaining;

    private List<ExpenseResponse> recentExpenses;
}