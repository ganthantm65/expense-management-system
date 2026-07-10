package com.expense.ExpenseManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySummary{

    private Integer month;

    private BigDecimal expense;

    private BigDecimal gst;

    private BigDecimal tds;

}
