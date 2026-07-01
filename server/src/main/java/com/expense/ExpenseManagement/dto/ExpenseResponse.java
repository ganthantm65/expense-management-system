package com.expense.ExpenseManagement.dto;

import com.expense.ExpenseManagement.Model.ExpenseStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ExpenseResponse {

    private Long expenseId;
    private String employeeName;
    private String category;
    private BigDecimal amount;
    private String description;
    private LocalDate expenseDate;
    private ExpenseStatus status;
    private String receiptUrl;
    private String approvedBy;
    private LocalDateTime approvedDate;
    private String remarks;

    public ExpenseResponse() {
    }

    public ExpenseResponse(
            Long expenseId,
            String employeeName,
            String category,
            BigDecimal amount,
            String description,
            LocalDate expenseDate,
            ExpenseStatus status,
            String receiptUrl,
            String approvedBy,
            LocalDateTime approvedDate,
            String remarks) {

        this.expenseId = expenseId;
        this.employeeName = employeeName;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.expenseDate = expenseDate;
        this.status = status;
        this.receiptUrl = receiptUrl;
        this.approvedBy = approvedBy;
        this.approvedDate = approvedDate;
        this.remarks = remarks;
    }
}