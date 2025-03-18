package com.expense.ExpenseManagement.Model;

import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeResponse {
    private String email;
    private String employeeName;
    private String Department;
    private String designation;
    private List<Expense> expenses=new ArrayList<>();

    public EmployeeResponse(String email, String employeeName, String department, String designation, List<Expense> expenses) {
        this.email = email;
        this.employeeName = employeeName;
        Department = department;
        this.designation = designation;
        this.expenses = expenses;
    }
}