package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BudgetRequest {

    private String department;

    private Double monthlyLimit;

    private Double warningLimit;

    private Integer month;

    private Integer year;
}
