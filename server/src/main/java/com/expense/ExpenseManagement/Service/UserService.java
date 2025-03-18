package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // First try loading the user from the Admin service
        try {
            return adminService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            // If not found, try loading from the Employee service
            return employeeService.loadUserByUsername(username);
        }
    }
}
