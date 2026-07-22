package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Configration.JwtService;
import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Service.AuthService;
import com.expense.ExpenseManagement.dto.LoginRequest;
import com.expense.ExpenseManagement.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody LoginRequest request) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            Admin admin=authService.findAdminByEmail(userDetails.getUsername());

            LoginResponse response = LoginResponse.builder()
                    .token(token)
                    .email(userDetails.getUsername())
                    .id(admin.getAdmin_id())
                    .role("ADMIN")
                    .message("Admin Login Successful")
                    .build();

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Email or Password");
        }
    }

    @PostMapping("/employee/login")
    public ResponseEntity<?> employeeLogin(@RequestBody LoginRequest request) {

        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            String token = jwtService.generateToken(userDetails);

            LoginResponse response = LoginResponse.builder()
                    .token(token)
                    .email(userDetails.getUsername())
                    .role("EMPLOYEE")
                    .message("Employee Login Successful")
                    .build();

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid Email or Password");
        }
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {

        return ResponseEntity.ok(
                authService.registerAdmin(admin)
        );
    }

    @PostMapping("/employee/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {

        return ResponseEntity.ok(
                authService.registerEmployee(employee)
        );
    }
}