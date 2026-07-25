package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AdminYearlyReport {

    private Integer year;

    private BigDecimal totalExpense;

    private BigDecimal totalGST;

    private BigDecimal totalTDS;

    private Double totalBudget;

    private List<MonthlySummary> monthlySummary;
}
