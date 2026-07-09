package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "tax_configuration")
public class TaxConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tax_id;

    @Column(unique = true)
    private String category;

    private BigDecimal gstRate;

    private BigDecimal tdsRate;

    private BigDecimal deductibleRate;

    private Boolean active;
}
