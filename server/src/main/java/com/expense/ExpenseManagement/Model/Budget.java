package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
@Data
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Integer budgetId;

    private String department;

    @Column(name = "monthly_limit")
    private Double monthlyLimit;

    @Column(name = "current_spent")
    private Double currentSpent;

    @Column(name = "warning_limit")
    private Double warningLimit;

    private String month;

    private String year;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}