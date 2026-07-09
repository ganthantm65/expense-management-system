package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.AuditLog;
import com.expense.ExpenseManagement.dto.AuditResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuditRepo extends JpaRepository<AuditLog,Long> {
    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.AuditResponse(
        a.auditId,
        e.expenseId,
        emp.employeeName,
        ad.adminName,
        a.action,
        a.oldStatus,
        a.newStatus,
        a.changedBy,
        a.changedAt,
        a.remarks,
        a.expenseAmount,
        a.budgetBefore,
        a.budgetAfter,
        a.gstRate,
        a.gstAmount,
        a.cgst,
        a.sgst,
        a.igst,
        a.tdsRate,
        a.tdsAmount,
        a.deductibleAmount,
        a.netExpense,
        a.taxRemarks
        )
        FROM AuditLog a
        JOIN a.expense e
        JOIN a.employee emp
        LEFT JOIN a.admin ad
        """)
    Page<AuditResponse> getAllAuditLogs(Pageable pageable);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.AuditResponse(
        a.auditId,
        e.expenseId,
        emp.employeeName,
        ad.adminName,
        a.action,
        a.oldStatus,
        a.newStatus,
        a.changedBy,
        a.changedAt,
        a.remarks,
        a.expenseAmount,
        a.budgetBefore,
        a.budgetAfter,
        a.gstRate,
        a.gstAmount,
        a.cgst,
        a.sgst,
        a.igst,
        a.tdsRate,
        a.tdsAmount,
        a.deductibleAmount,
        a.netExpense,
        a.taxRemarks
        )
        FROM AuditLog a
        JOIN a.expense e
        JOIN a.employee emp
        LEFT JOIN a.admin ad
        WHERE e.expenseId = :expenseId
        """)
    Page<AuditResponse> getAuditByExpense(Long expenseId, Pageable pageable);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.AuditResponse(
        a.auditId,
        e.expenseId,
        emp.employeeName,
        ad.adminName,
        a.action,
        a.oldStatus,
        a.newStatus,
        a.changedBy,
        a.changedAt,
        a.remarks,
        a.expenseAmount,
        a.budgetBefore,
        a.budgetAfter,
        a.gstRate,
        a.gstAmount,
        a.cgst,
        a.sgst,
        a.igst,
        a.tdsRate,
        a.tdsAmount,
        a.deductibleAmount,
        a.netExpense,
        a.taxRemarks
        )
        FROM AuditLog a
        JOIN a.expense e
        JOIN a.employee emp
        LEFT JOIN a.admin ad
        WHERE emp.employeeId = :employeeId
        """)
    Page<AuditResponse> getEmployeeAudit(Integer employeeId, Pageable pageable);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.AuditResponse(
        a.auditId,
        e.expenseId,
        emp.employeeName,
        ad.adminName,
        a.action,
        a.oldStatus,
        a.newStatus,
        a.changedBy,
        a.changedAt,
        a.remarks,
        a.expenseAmount,
        a.budgetBefore,
        a.budgetAfter,
        a.gstRate,
        a.gstAmount,
        a.cgst,
        a.sgst,
        a.igst,
        a.tdsRate,
        a.tdsAmount,
        a.deductibleAmount,
        a.netExpense,
        a.taxRemarks
        )
        FROM AuditLog a
        JOIN a.expense e
        JOIN a.employee emp
        LEFT JOIN a.admin ad
        WHERE ad.admin_id = :adminId
        """)
    Page<AuditResponse> getAdminAudit(Integer adminId, Pageable pageable);

}
