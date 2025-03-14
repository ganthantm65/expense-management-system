package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Service.AdminService;
import com.expense.ExpenseManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ExpenseController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/admin/login")
    public ResponseEntity<?> validateAdmin(@RequestBody Admin admin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(adminService.loadUserByUsername(admin.getEmail()));
            } else {
                return ResponseEntity.status(401).body("Invalid Credentials");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }
    @PostMapping("employee/login")
    public ResponseEntity<?> validateUser(@RequestBody  Employee employee) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword())
            );

            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(employeeService.loadUserByUsername(employee.getEmail()));
            } else {
                return ResponseEntity.status(401).body("Invalid Credentials");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }

    @PostMapping("/admin/register")
    public Admin registerAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }
    @PostMapping("/employee/register")
    public Employee registerEmployee(@RequestBody Employee employee){
        return employeeService.registerEmployee(employee);
    }
}
