package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class MonthlyReportResponse {

    private String employeeName;

    private Integer month;

    private Integer year;

    private BigDecimal totalExpense;

    private Long approvedExpenses;

    private Long rejectedExpenses;

    private Long pendingExpenses;

    private BigDecimal gst;

    private BigDecimal tds;

    private BigDecimal deductible;

    private BigDecimal netExpense;

    private List<ExpenseResponse> expenses;

}
