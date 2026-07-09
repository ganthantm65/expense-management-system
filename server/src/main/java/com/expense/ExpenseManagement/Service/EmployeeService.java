package com.expense.ExpenseManagement.Service;


import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Repository.EmployeeRepo;
import com.expense.ExpenseManagement.dto.EmployeeProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeProfile getEmployeeProfile(String email) throws Exception{
        Employee employee=employeeRepo.findByEmail(email);

        if (employee==null){
            throw new Exception("Employee Not Found");
        }

        EmployeeProfile employeeProfile=new EmployeeProfile();

        employeeProfile.setEmployeeName(employee.getEmployeeName());
        employeeProfile.setEmail(employee.getEmail());
        employeeProfile.setDepartment(employee.getDepartment());
        employeeProfile.setPhone(employee.getPhone());
        employeeProfile.setId(employee.getEmployeeId());
        employeeProfile.setDesignation(employee.getDesignation());
        employeeProfile.setStatus(employee.getStatus());

        return employeeProfile;
    }

    public Map<String, String> updateProfile(EmployeeProfile employeeProfile) throws Exception {

        Employee employee = employeeRepo.findByEmail(employeeProfile.getEmail());

        if (employee == null) {
            throw new Exception("Employee Not Found");
        }

        if (employeeProfile.getEmployeeName() != null) {
            employee.setEmployeeName(employeeProfile.getEmployeeName());
        }

        if (employeeProfile.getPhone() != null) {
            employee.setPhone(employeeProfile.getPhone());
        }

        if (employeeProfile.getDepartment() != null) {
            employee.setDepartment(employeeProfile.getDepartment());
        }

        if (employeeProfile.getDesignation() != null) {
            employee.setDesignation(employeeProfile.getDesignation());
        }

        if (employeeProfile.getStatus() != null) {
            employee.setStatus(employeeProfile.getStatus());
        }

        employeeRepo.save(employee);

        return Map.of("message", "Updated Successfully");
    }

    public List<EmployeeProfile> getAllEmployeeProfiles(){
        List<EmployeeProfile> profiles=new ArrayList<>();

        List<Employee> employees=employeeRepo.findAll();

        for (Employee employee:employees){
            EmployeeProfile employeeProfile=new EmployeeProfile();

            employeeProfile.setEmployeeName(employee.getEmployeeName());
            employeeProfile.setEmail(employee.getEmail());
            employeeProfile.setDepartment(employee.getDepartment());
            employeeProfile.setPhone(employee.getPhone());
            employeeProfile.setId(employee.getEmployeeId());
            employeeProfile.setDesignation(employee.getDesignation());
            employeeProfile.setStatus(employee.getStatus());

            profiles.add(employeeProfile);
        }

        return profiles;
    }

    public EmployeeProfile getEmployeeByName(String name)throws Exception{
        Employee employee=employeeRepo.findByEmployeeName(name);
        if (employee==null){
            throw new Exception("Employee Not Found");
        }
        EmployeeProfile employeeProfile=new EmployeeProfile();

        employeeProfile.setEmployeeName(employee.getEmployeeName());
        employeeProfile.setEmail(employee.getEmail());
        employeeProfile.setDepartment(employee.getDepartment());
        employeeProfile.setPhone(employee.getPhone());
        employeeProfile.setId(employee.getEmployeeId());
        employeeProfile.setDesignation(employee.getDesignation());
        employeeProfile.setStatus(employee.getStatus());
        return employeeProfile;
    }

    public Map<String,String> updatedEmployeeStatus(int id,String status)throws Exception{
        Employee employee=employeeRepo.findById(id);
        if (employee==null){
            throw new Exception("Employee Not Found");
        }
        employee.setStatus(status);

        return Map.of("message","Status Updated Successfully");
    }


}