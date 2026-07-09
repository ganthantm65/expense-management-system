package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Model.Admin;
import com.expense.ExpenseManagement.Model.AuditLog;
import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.Model.ExpenseStatus;
import com.expense.ExpenseManagement.Repository.AuditRepo;
import com.expense.ExpenseManagement.dto.AuditResponse;
import com.expense.ExpenseManagement.dto.ExpenseTaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditService {

    @Autowired
    private AuditRepo auditRepo;

    @Autowired
    private TaxService taxService;

    public void logExpenseAction(
            Expense expense,
            Admin admin,
            ExpenseStatus oldStatus,
            ExpenseStatus newStatus,
            String action,
            String remarks){

        ExpenseTaxResponse tax =
                taxService.calculateTax(expense);

        AuditLog audit = new AuditLog();

        audit.setExpense(expense);
        audit.setEmployee(expense.getEmployee());
        audit.setAdmin(admin);

        audit.setAction(action);
        audit.setOldStatus(oldStatus);
        audit.setNewStatus(newStatus);
        audit.setChangedBy(admin.getAdminName());
        audit.setChangedAt(LocalDateTime.now());
        audit.setRemarks(remarks);

        audit.setGstRate(tax.getGstRate());
        audit.setGstAmount(tax.getGstAmount());
        audit.setCgst(tax.getCgst());
        audit.setSgst(tax.getSgst());
        audit.setIgst(tax.getIgst());
        audit.setTdsRate(tax.getTdsRate());
        audit.setTdsAmount(tax.getTdsAmount());
        audit.setDeductibleAmount(tax.getDeductibleAmount());
        audit.setTaxRemarks(tax.getRemarks());

        auditRepo.save(audit);
    }
    public Page<AuditResponse> getAllAuditLogs(Pageable pageable) {
        return auditRepo.getAllAuditLogs(pageable);
    }

    public Page<AuditResponse> getExpenseAudit(Long expenseId,
                                               Pageable pageable) {
        return auditRepo.getAuditByExpense(expenseId, pageable);
    }

    public Page<AuditResponse> getEmployeeAudit(Integer employeeId,
                                                Pageable pageable) {
        return auditRepo.getEmployeeAudit(employeeId, pageable);
    }

    public Page<AuditResponse> getAdminAudit(Integer adminId,
                                             Pageable pageable) {
        return auditRepo.getAdminAudit(adminId, pageable);
    }
}
