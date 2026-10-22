package com.expense.ExpenseManagement.dto;

import lombok.Data;

@Data
public class EmployeeProfile{
    private int id;
    private String employeeName;
    private String email;
    private String department;
    private String designation;
    private String phone;
    private String status;
}
