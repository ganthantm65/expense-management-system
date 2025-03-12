package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Admin")
public class Admin {

    private String adminName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    private String password;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
