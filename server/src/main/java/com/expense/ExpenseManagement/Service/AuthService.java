package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.Repository.AdminRepo;
import com.expense.ExpenseManagement.Repository.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.StringBufferInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepo adminRepo;
    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    public Map<String,String> registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepo.save(admin);
        Map<String,String> response=new HashMap<>();
        response.put("message","Employee Profile Created Successfully");
        return  response;
    }

    public Map<String,String> registerEmployee(Employee employee) {
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepo.save(employee);
        Map<String,String> response=new HashMap<>();
        response.put("message","Admin Profile Created Successfully");
        return response;
    }

    public Admin findAdminByEmail(String email) {
        return adminRepo.findByEmail(email);
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeRepo.findByEmail(email);
    }
}