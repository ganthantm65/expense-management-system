package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.Repository.OtpUtil;
import com.expense.ExpenseManagement.Service.AdminService;
import com.expense.ExpenseManagement.Service.EmployeeService;
import com.expense.ExpenseManagement.Service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class ExpenseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MailService mailService;

    @Autowired
    private OtpUtil otpUtil;

    private Map<String, String> otpMap = new HashMap<>();

    @PostMapping("/admin/login")
    public ResponseEntity<?> validateAdmin(@RequestBody Admin admin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(admin.getEmail(), admin.getPassword())
            );

            if (authentication.isAuthenticated()) {
                Admin authenticatedAdmin = adminService.findAdminByEmail(admin.getEmail());
                return ResponseEntity.ok(authenticatedAdmin);
            } else {
                return ResponseEntity.status(401).body("Invalid Credentials");
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> validateUser(@RequestBody Employee employee) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword())
            );

            if (authentication.isAuthenticated()) {
                Employee authenticatedEmployee = employeeService.findEmployeeByEmail(employee.getEmail());
                return ResponseEntity.ok(authenticatedEmployee);
            } else {
                return ResponseEntity.status(401).body("Invalid Credentials");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid Credentials");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }

    @PostMapping("/admin/register")
    public Admin registerAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

    @PostMapping("/employee/register")
    public Employee registerEmployee(@RequestBody Employee employee) {
        return employeeService.registerEmployee(employee);
    }

    @PostMapping("/otp/send")
    public ResponseEntity<?> sendOtpToMail(@RequestBody Map<String, String> request) {
        try {
            String email = request.get("email");
            String otp = otpUtil.generateOtp();
            otpMap.put(email, otp);
            mailService.sendMail(email, otp);
            return ResponseEntity.ok("OTP sent");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending OTP");
        }
    }

    @PostMapping("/otp/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        if (otpMap.containsKey(email) && otpMap.get(email).equals(otp)) {
            otpMap.remove(email);
            return ResponseEntity.ok("OTP verified successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid OTP");
        }
    }

    @PostMapping("/employee/expense")
    public ResponseEntity<Map<String, Object>> addExpenses(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.addExpenses(employee.getEmployeeName(), employee.getExpenses());

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Expenses added successfully");
            response.put("employee", updatedEmployee);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());

            return ResponseEntity.status(404).body(errorResponse);
        }

    }

    @PutMapping("/employee/expense")
    public ResponseEntity<Map<String, Object>> updateAllExpenses(@RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateAllExpenses(employee.getEmployeeName(), employee.getExpenses());

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Expenses added successfully");
            response.put("employee", updatedEmployee);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());

            return ResponseEntity.status(404).body(errorResponse);
        }
    }

    @GetMapping("/employee/getEmployees")
    public List<Employee> getEmployees(){
        return employeeService.getEmployeeData();
    }

    @GetMapping("/admin/getAdmin")
    public List<Admin> getAdmin(){
        return adminService.getAdminDatas();
    }

    @GetMapping("/employee/{employeeName}")
    public Employee getEmployeeByName(@PathVariable String employeeName){
        return employeeService.getEmployeeByName(employeeName);
    }
}