package com.expense.ExpenseManagement.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class YearlyReportResponse {

    private Integer year;

    private BigDecimal totalExpense;

    private BigDecimal totalGST;

    private BigDecimal totalTDS;

    private List<MonthlySummaryDTO> months;

}