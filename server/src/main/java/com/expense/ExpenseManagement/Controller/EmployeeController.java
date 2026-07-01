package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Configration.JwtService;
import com.expense.ExpenseManagement.Service.EmployeeService;
import com.expense.ExpenseManagement.dto.EmployeeProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/profile")
    public ResponseEntity<?> getEmployeeProfile(
            @RequestHeader("Authorization") String authorization
    ) {
        try {
            String token = authorization.replace("Bearer ", "");
            String email = jwtService.extractUsername(token);

            EmployeeProfile profile = employeeService.getEmployeeProfile(email);

            return ResponseEntity.ok(profile);

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            @RequestHeader("Authorization") String authorization,
            @RequestBody EmployeeProfile employeeProfile
    ) {
        try {
            String token = authorization.replace("Bearer ", "");
            String email = jwtService.extractUsername(token);

            employeeProfile.setEmail(email);

            return ResponseEntity.ok(employeeService.updateProfile(employeeProfile));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeProfile>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployeeProfiles());
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getEmployeeByName(@RequestParam String name){
        try{
            return ResponseEntity.ok().body(employeeService.getEmployeeByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateEmployeeStatus(
            @PathVariable int id,
            @RequestParam String status
    ) {
        try {
            return ResponseEntity.ok(employeeService.updatedEmployeeStatus(id, status));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("message", e.getMessage()));
        }
    }
}