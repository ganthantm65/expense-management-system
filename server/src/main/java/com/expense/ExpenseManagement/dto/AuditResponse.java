package com.expense.ExpenseManagement.dto;

import com.expense.ExpenseManagement.Model.ExpenseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AuditResponse {

    private Long auditId;

    private Long expenseId;

    private String employeeName;

    private String adminName;

    private String action;

    private ExpenseStatus oldStatus;

    private ExpenseStatus newStatus;

    private String changedBy;

    private LocalDateTime changedAt;

    private String remarks;

    private BigDecimal expenseAmount;

    private BigDecimal budgetBefore;

    private BigDecimal budgetAfter;

    private BigDecimal gstRate;

    private BigDecimal gstAmount;

    private BigDecimal cgst;

    private BigDecimal sgst;

    private BigDecimal igst;

    private BigDecimal tdsRate;

    private BigDecimal tdsAmount;

    private BigDecimal deductibleAmount;

    private BigDecimal netExpense;

    private String taxRemarks;

}