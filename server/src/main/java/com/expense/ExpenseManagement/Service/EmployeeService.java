package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Employee employee = employeeRepo.findByEmail(email);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        return User.builder()
                .username(employee.getEmployeeName())
                .password(employee.getPassword())
                .roles("EMPLOYEE")
                .build();
    }

    public Employee registerEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepo.save(employee);
    }
}
