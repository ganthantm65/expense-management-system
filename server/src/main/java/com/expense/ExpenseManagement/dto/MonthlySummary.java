package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MonthlySummaryDTO {

    private Integer month;

    private BigDecimal expense;

    private BigDecimal gst;

    private BigDecimal tds;

}
