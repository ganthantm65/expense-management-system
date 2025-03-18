package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Model.EmployeeResponse;
import com.expense.ExpenseManagement.Repository.OtpUtil;
import com.expense.ExpenseManagement.Service.AdminService;
import com.expense.ExpenseManagement.Service.EmployeeService;
import com.expense.ExpenseManagement.Service.MailService;
import com.expense.ExpenseManagement.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    private Map<String,String> otpMap=new HashMap<>();

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
    public ResponseEntity<?> validateUser(@RequestBody Employee employee) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(employee.getEmail(), employee.getPassword())
            );
            if (!authentication.isAuthenticated()){
                return ResponseEntity.status(401).body("Invalid Credential");
            }
            Employee employee1=employeeService.findByEmail(employee.getEmail());
            EmployeeResponse response =new EmployeeResponse(employee1.getEmail(),employee1.getEmployeeName(),employee1.getDepartment(),employee1.getDesignation(),employee1.getExpenses());
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
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
    public Employee registerEmployee(@RequestBody Employee employee){
        return employeeService.registerEmployee(employee);
    }
    @PostMapping("/otp/send")
    public ResponseEntity<?> sendOtpToMail(@RequestBody Admin admin){
        try {
            System.out.println("Received request to send OTP to: " + admin.getEmail());
            String otp = otpUtil.generateOtp();
            otpMap.put(admin.getEmail(),otp);
            System.out.println("Generated OTP: " + otp);
            mailService.sendMail(admin.getEmail(), otp);
            return ResponseEntity.ok("OTP sent");
        } catch (Exception e) {
            System.err.println("Error sending OTP: " + e.getMessage());
            return ResponseEntity.status(500).body("Error sending OTP");
        }
    }
    @PostMapping("/otp/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        System.out.println("OTP Map: " + otpMap);

        if (otpMap.containsKey(email) && otpMap.get(email).equals(otp)) {
            otpMap.remove(email);
            return ResponseEntity.ok("Your OTP is verified successfully");
        } else {
            return ResponseEntity.status(401).body("Invalid OTP");
        }
    }
}
