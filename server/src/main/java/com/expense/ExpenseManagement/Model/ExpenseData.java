package com.expense.ExpenseManagement.Model;

import java.util.List;

public class ExpenseData {
    private String userName;
    private String email;
    private List<Expense> expenses;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "ExpenseData{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", expenses=" + expenses +
                '}';
    }
}
