package com.expense.ExpenseManagement.dto;

import lombok.Data;

@Data
public class BudgetReport {

    private String department;

    private Double monthlyLimit;

    private Double spent;

    private Double remaining;

    private Double utilizationPercentage;
}
