package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseTaxResponse {
    private BigDecimal gstRate;

    private BigDecimal gstAmount;

    private BigDecimal cgst;

    private BigDecimal sgst;

    private BigDecimal igst;

    private BigDecimal tdsRate;

    private BigDecimal tdsAmount;

    private BigDecimal deductibleAmount;

    private String remarks;
}
