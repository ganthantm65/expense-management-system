package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Admin registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepo.save(admin);
    }

    public Admin findAdminByEmail(String email) {
        return adminRepo.findByEmail(email);
    }
}