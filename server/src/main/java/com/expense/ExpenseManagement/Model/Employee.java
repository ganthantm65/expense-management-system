package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

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
    private String Department;
    private String designation;

    @JdbcTypeCode(SqlTypes.JSON)
    private List<Expense> expenses=new ArrayList<>();

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
