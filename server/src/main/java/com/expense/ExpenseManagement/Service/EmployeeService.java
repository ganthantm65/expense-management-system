package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Employee registerEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }

    public Employee addExpenses(String employeeName, List<Expense> newExpenses) {
        Employee employee = employeeRepo.findByEmployeeName(employeeName);
        if (employee == null) {
            throw new RuntimeException("Employee not found: " + employeeName);
        }
        employee.setExpenses(newExpenses);
        return employeeRepo.save(employee);
    }

    public Employee updateAllExpenses(String employeeName, List<Expense> updatedExpenses) {
        Employee employee = employeeRepo.findByEmployeeName(employeeName);
        if (employee == null) {
            throw new RuntimeException("Employee not found: " + employeeName);
        }
        employee.setExpenses(updatedExpenses);
        return employeeRepo.save(employee);
    }
}