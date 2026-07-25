package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TaxReport {

    private BigDecimal gst;

    private BigDecimal tds;

    private BigDecimal netExpense;

}