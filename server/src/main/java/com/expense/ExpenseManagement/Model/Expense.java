package com.expense.ExpenseManagement.Model;

import lombok.Data;

@Data
public class Expense {
    private String Category;
    private double amount;
    private String description;
    private String dateOfExpense;

    @Override
    public String toString() {
        return "Expense{" +
                "Category='" + Category + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", dateOfExpense='" + dateOfExpense + '\'' +
                '}';
    }
}
