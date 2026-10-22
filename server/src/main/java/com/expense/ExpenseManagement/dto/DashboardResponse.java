package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DashboardResponse {

    private Long totalEmployees;

    private Long totalExpenses;

    private Long approvedExpenses;

    private Long pendingExpenses;

    private Long rejectedExpenses;

    private BigDecimal totalExpenseAmount;

    private Double monthlyBudget;

    private Double currentSpent;

    private Double remainingBudget;

    private Double budgetUtilization;

    private BigDecimal totalGST;

    private BigDecimal totalTDS;
}
