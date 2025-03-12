package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String employeeName;
    private String email;
    private String password;

    public Employee() {}

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", userName='" + employeeName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
