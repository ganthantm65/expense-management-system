package com.expense.ExpenseManagement.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String category;

    private BigDecimal amount;

    private String description;

    private LocalDate expenseDate;

    @Enumerated(EnumType.STRING)
    private ExpenseStatus status;

    private String receiptUrl;

    @ManyToOne
    @JoinColumn(name = "approved_by")
    private Admin approvedBy;

    private LocalDateTime approvedDate;

    private String remarks;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}