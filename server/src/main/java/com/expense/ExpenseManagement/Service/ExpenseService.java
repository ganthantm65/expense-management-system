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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Autowired
    private BudgetService budgetService;

    @Autowired
    private AuditService auditService;

    /*====================================================
                    CREATE EXPENSE
    ====================================================*/

    @Transactional
    public ExpenseResponse createExpense(
            Integer employeeId,
            ExpenseRequest request
    ) throws Exception {

        Optional<Employee> employee = employeeRepo.findById(employeeId);

        if (employee.get() == null) {
            throw new RuntimeException("Employee not found");
        }

        if (request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be greater than zero");
        }

        boolean available = budgetService.validateBudget(
                employee.get().getDepartment(),
                request.getExpenseDate().getMonthValue(),
                request.getExpenseDate().getYear(),
                request.getAmount().doubleValue()
        );

        if (!available) {
            throw new RuntimeException(
                    "Department budget exceeded"
            );
        }

        Expense expense = new Expense();

        expense.setEmployee(employee.get());
        expense.setCategory(request.getCategory());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setStatus(ExpenseStatus.DRAFT);
        expense.setReceiptUrl(null);
        expense.setCreatedAt(LocalDateTime.now());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepo.save(expense);

        auditService.logExpenseAction(
                expense,
                null,
                null,
                ExpenseStatus.DRAFT,
                "CREATED",
                "Expense Created"
        );

        ExpenseResponse response = new ExpenseResponse();

        response.setExpenseId(expense.getExpenseId());
        response.setEmployeeName(employee.get().getEmployeeName());
        response.setCategory(expense.getCategory());
        response.setAmount(expense.getAmount());
        response.setDescription(expense.getDescription());
        response.setExpenseDate(expense.getExpenseDate());
        response.setStatus(expense.getStatus());
        response.setReceiptUrl(expense.getReceiptUrl());

        return response;
    }

    /*====================================================
                    UPDATE EXPENSE
    ====================================================*/

    @Transactional
    public Map<String,String> updateExpense(
            Long expenseId,
            ExpenseRequest request
    ){

        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() ->
                        new RuntimeException("Expense not found"));

        if(expense.getStatus()!=ExpenseStatus.DRAFT){
            throw new RuntimeException(
                    "Only draft expenses can be updated."
            );
        }

        if(request.getAmount().compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException(
                    "Amount must be greater than zero."
            );
        }

        ExpenseStatus oldStatus = expense.getStatus();

        expense.setCategory(request.getCategory());
        expense.setAmount(request.getAmount());
        expense.setDescription(request.getDescription());
        expense.setExpenseDate(request.getExpenseDate());
        expense.setUpdatedAt(LocalDateTime.now());

        expenseRepo.save(expense);

        auditService.logExpenseAction(
                expense,
                null,
                oldStatus,
                expense.getStatus(),
                "UPDATED",
                "Expense Updated"
        );

        return Map.of(
                "message",
                "Expense updated successfully."
        );
    }

    /*====================================================
                    DELETE EXPENSE
    ====================================================*/

    @Transactional
    public Map<String,String> deleteExpense(
            Long expenseId
    ){

        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() ->
                        new RuntimeException("Expense not found"));

        if(expense.getStatus()!=ExpenseStatus.DRAFT){
            throw new RuntimeException(
                    "Only draft expenses can be deleted."
            );
        }

        auditService.logExpenseAction(
                expense,
                null,
                expense.getStatus(),
                expense.getStatus(),
                "DELETED",
                "Expense Deleted"
        );

        expenseRepo.delete(expense);

        return Map.of(
                "message",
                "Expense deleted successfully."
        );
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

    public Map<String, String> approveExpense(
            Long expenseId,
            Long adminId,
            ExpenseApproval expenseApproval
    ) throws Exception {

        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new Exception("Expense Not Found"));

        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new Exception("Admin not found"));

        ExpenseStatus oldStatus = expense.getStatus();

        expense.setStatus(ExpenseStatus.APPROVED);
        expense.setApprovedBy(admin);
        expense.setExpenseDate(LocalDate.now());
        expense.setRemarks(expenseApproval.getRemarks());

        expenseRepo.save(expense);

        auditService.logExpenseAction(
                expense,
                admin,
                oldStatus,
                ExpenseStatus.APPROVED,
                "APPROVED",
                expenseApproval.getRemarks()
        );

        return Map.of("message", "Updated Successfully");
    }

    public Map<String, String> rejectExpense(
            Long expenseId,
            Long adminId,
            ExpenseApproval expenseApproval
    ) throws Exception {

        Expense expense = expenseRepo.findById(expenseId)
                .orElseThrow(() -> new Exception("Expense Not Found"));

        Admin admin = adminRepo.findById(adminId)
                .orElseThrow(() -> new Exception("Admin not found"));

        ExpenseStatus oldStatus = expense.getStatus();

        expense.setStatus(ExpenseStatus.REJECTED);
        expense.setRemarks(expenseApproval.getRemarks());
        expense.setApprovedBy(admin);
        expense.setExpenseDate(LocalDate.from(LocalDateTime.now()));

        expenseRepo.save(expense);

        auditService.logExpenseAction(
                expense,
                admin,
                oldStatus,
                ExpenseStatus.REJECTED,
                "REJECTED",
                expenseApproval.getRemarks()
        );

        return Map.of("message", "Updated Successfully");
    }

    public Page<ExpenseResponse> getAllExpenses(Pageable pageable) {
        return expenseRepo.getAllExpenses(pageable);
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
