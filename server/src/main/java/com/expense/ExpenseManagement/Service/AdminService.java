package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements UserDetailsService {
    @Autowired
    private AdminRepo adminRepo;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminService(AdminRepo adminRepo, BCryptPasswordEncoder passwordEncoder) {
        this.adminRepo = adminRepo;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public UserDetails loadUserByUsername(String email) {

        Admin admin=adminRepo.findByEmail(email);
        return User.builder()
                .username(admin.getAdminName())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
    public Admin registerAdmin(Admin admin){
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepo.save(admin);
        return admin;
    }
}
