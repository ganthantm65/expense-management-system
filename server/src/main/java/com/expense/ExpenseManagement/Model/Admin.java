package com.expense.ExpenseManagement.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "Admin")
public class Admin {

    private String adminName;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admin_id;

    private String email;

    private String password;

    private Date createdAt;

    private String profile;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminName='" + adminName + '\'' +
                ", id=" + admin_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
