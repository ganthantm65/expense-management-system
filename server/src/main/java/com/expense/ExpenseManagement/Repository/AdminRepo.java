package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepo extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}

