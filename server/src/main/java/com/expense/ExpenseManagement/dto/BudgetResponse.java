package com.expense.ExpenseManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetResponse {

    private Integer budgetId;

    private String department;

    private Double monthlyLimit;

    private Double currentSpent;

    private Double warningLimit;

    private String month;

    private String year;
}