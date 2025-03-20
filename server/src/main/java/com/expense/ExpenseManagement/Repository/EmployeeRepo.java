package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
    Employee findByEmployeeName(String employeeName);
}
