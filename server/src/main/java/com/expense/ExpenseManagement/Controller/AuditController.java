package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Service.AuditService;
import com.expense.ExpenseManagement.dto.AuditResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuditController {

    @Autowired
    private AuditService auditService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/audit")
    public Page<AuditResponse> getAllAuditLogs(Pageable pageable) {
        return auditService.getAllAuditLogs(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/audit/expense/{expenseId}")
    public Page<AuditResponse> getExpenseAudit(
            @PathVariable Long expenseId,
            Pageable pageable) {

        return auditService.getExpenseAudit(expenseId, pageable);
    }

    @PreAuthorize("hasRole('EMPLOYEE')")
    @GetMapping("/employee/audit/{employeeId}")
    public Page<AuditResponse> getEmployeeAudit(
            @PathVariable Integer employeeId,
            Pageable pageable) {

        return auditService.getEmployeeAudit(employeeId, pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/audit/{adminId}")
    public Page<AuditResponse> getAdminAudit(
            @PathVariable Integer adminId,
            Pageable pageable) {

        return auditService.getAdminAudit(adminId, pageable);
    }
}