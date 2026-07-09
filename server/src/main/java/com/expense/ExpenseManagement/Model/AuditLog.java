package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    private String action;

    @Enumerated(EnumType.STRING)
    private ExpenseStatus oldStatus;

    @Enumerated(EnumType.STRING)
    private ExpenseStatus newStatus;

    private String changedBy;

    private LocalDateTime changedAt;

    @Column(length = 500)
    private String remarks;

    /* Tax Snapshot */

    private BigDecimal gstRate;

    private BigDecimal cgst;

    private BigDecimal sgst;

    private BigDecimal igst;

    private BigDecimal tdsRate;

    private BigDecimal budgetBefore;

    private BigDecimal budgetAfter;

    private BigDecimal expenseAmount;

    private BigDecimal gstAmount;

    private BigDecimal tdsAmount;

    private BigDecimal deductibleAmount;

    private BigDecimal netExpense;

    private String taxRemarks;
}