package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.Employee;
import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.Model.ExpenseStatus;
import com.expense.ExpenseManagement.Repository.AdminRepo;
import com.expense.ExpenseManagement.Repository.EmployeeRepo;
import com.expense.ExpenseManagement.Repository.ExpenseRepo;
import com.expense.ExpenseManagement.dto.ExpenseApproval;
import com.expense.ExpenseManagement.dto.ExpenseRequest;
import com.expense.ExpenseManagement.dto.ExpenseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private AdminRepo adminRepo;

    public ExpenseResponse createExpense(
            int employeeId,
            ExpenseRequest expenseRequest
    )throws Exception{
        Expense expense=new Expense();

        Employee employee=employeeRepo.findById(employeeId);

        if(employee==null){
            throw new Exception("Employee Not Found");
        }

        if (expenseRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero.");
        }

        expense.setEmployee(employee);
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setDescription(expenseRequest.getDescription());
        expense.setExpenseDate(expenseRequest.getExpenseDate());
        expense.setStatus(ExpenseStatus.DRAFT);
        expense.setReceiptUrl(null);
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepo.save(expense);

        ExpenseResponse response = new ExpenseResponse();

        response.setExpenseId(expense.getExpenseId());
        response.setEmployeeName(employee.getEmployeeName());
        response.setCategory(expense.getCategory());
        response.setAmount(expense.getAmount());
        response.setDescription(expense.getDescription());
        response.setExpenseDate(expense.getExpenseDate());
        response.setStatus(expense.getStatus());
        response.setReceiptUrl(expense.getReceiptUrl());

        return response;
    }

    public Map<String, String> updateExpense(Long id, ExpenseRequest expenseRequest) {

        Expense expense = expenseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        if (expense.getStatus() != ExpenseStatus.DRAFT) {
            throw new RuntimeException("Only draft expenses can be updated.");
        }

        if (expenseRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero.");
        }

        expense.setCategory(expenseRequest.getCategory());
        expense.setAmount(expenseRequest.getAmount());
        expense.setDescription(expenseRequest.getDescription());
        expense.setExpenseDate(expenseRequest.getExpenseDate());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepo.save(expense);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Expense updated successfully.");

        return response;
    }

    public Map<String,String> deleteExpense(Long expenseId) throws Exception{
        Optional<Expense> expense=expenseRepo.findById(expenseId);

        if(expense==null){
            throw new Exception("Expense Not Found");
        }

        if(!(expense.get().getStatus()!=ExpenseStatus.DRAFT)){
            throw new Exception("Expense is not applicable to deleted");
        }

        expenseRepo.deleteById(expenseId);

        return Map.of("message","Expense Deleted Successfully");
    }

    public Map<String,String> submitExpense(Long expenseId) throws Exception{
        Optional<Expense> expense=expenseRepo.findById(expenseId);

        if(expense.get()==null){
            throw new Exception("Expense Not Found");
        }

        expense.get().setStatus(ExpenseStatus.SUBMITTED);

        expenseRepo.save(expense.get());

        return Map.of("message","Submitted Successfully");
    }

    public Map<String,String > approveExpense(
            Long expenseId,
            Long adminId,
            ExpenseApproval expenseApproval
    ) throws Exception{
        Optional<Expense> expense=expenseRepo.findById(expenseId);

        if(expense.get()==null){
            throw new Exception("Expense Not Found");
        }

        Admin admin=adminRepo.getById(adminId);

        if(admin==null){
            throw new Exception("Admin not found");
        }

        expense.get().setStatus(ExpenseStatus.APPROVED);
        expense.get().setRemarks(expenseApproval.getRemarks());

        expenseRepo.save(expense.get());

        return Map.of("message","Updated Successfully");
    }

    public Map<String,String > rejectExpense(
            Long expenseId,
            Long adminId,
            ExpenseApproval expenseApproval
    ) throws Exception{
        Optional<Expense> expense=expenseRepo.findById(expenseId);

        if(expense.get()==null){
            throw new Exception("Expense Not Found");
        }

        Admin admin=adminRepo.getById(adminId);

        if(admin==null){
            throw new Exception("Admin not found");
        }

        expense.get().setStatus(ExpenseStatus.REJECTED);
        expense.get().setRemarks(expenseApproval.getRemarks());

        expenseRepo.save(expense.get());

        return Map.of("message","Updated Successfully");
    }

    public List<ExpenseResponse> getAllExpenses() {
        return expenseRepo.getAllExpenses();
    }

    public ExpenseResponse getExpenseById(Long expenseId) throws Exception {

        return expenseRepo.getExpenseById(expenseId)
                .orElseThrow(() -> new Exception("Expense Not Found"));
    }

    public List<ExpenseResponse> getEmployeeExpenses(Long employeeId) {

        return expenseRepo.getEmployeeExpenses(employeeId);
    }

    public List<ExpenseResponse> getPendingExpenses() {

        return expenseRepo.getPendingExpenses();
    }
}
