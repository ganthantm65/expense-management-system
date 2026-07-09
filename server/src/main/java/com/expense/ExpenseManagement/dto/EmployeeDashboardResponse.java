package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class EmployeeDashboardResponse {

    private Long totalExpenses;

    private Long approvedExpenses;

    private Long pendingExpenses;

    private Long rejectedExpenses;

    private BigDecimal monthlyExpense;

    private BigDecimal yearlyExpense;

    private BigDecimal gst;

    private BigDecimal tds;

    private List<ExpenseResponse> recentExpenses;

}