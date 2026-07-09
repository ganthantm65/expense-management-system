package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
    Employee findByEmployeeName(String employeeName);
    Employee findById(int id);

    @Query("""
            SELECT COUNT(e)
            FROM Employee e
            """)
    Long getTotalEmployees();
}
