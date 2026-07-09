package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.dto.ExpenseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.ExpenseResponse(
            e.expenseId,
            emp.employeeName,
            e.category,
            e.amount,
            e.description,
            e.expenseDate,
            e.status,
            e.receiptUrl,
            a.adminName,
            e.approvedDate,
            e.remarks
        )
        FROM Expense e
        JOIN e.employee emp
        LEFT JOIN e.approvedBy a
    """)
    Page<ExpenseResponse> getAllExpenses(Pageable pageable);

    @Query("""
    SELECT new com.expense.ExpenseManagement.dto.ExpenseResponse(
        e.expenseId,
        emp.employeeName,
        e.category,
        e.amount,
        e.description,
        e.expenseDate,
        e.status,
        e.receiptUrl,
        a.adminName,
        e.approvedDate,
        e.remarks
    )
    FROM Expense e
    JOIN e.employee emp
    LEFT JOIN e.approvedBy a
    WHERE e.expenseId = :expenseId
""")
    Optional<ExpenseResponse> getExpenseById(Long expenseId);

    @Query("""
    SELECT new com.expense.ExpenseManagement.dto.ExpenseResponse(
        e.expenseId,
        emp.employeeName,
        e.category,
        e.amount,
        e.description,
        e.expenseDate,
        e.status,
        e.receiptUrl,
        a.adminName,
        e.approvedDate,
        e.remarks
    )
    FROM Expense e
    JOIN e.employee emp
    LEFT JOIN e.approvedBy a
    WHERE emp.id = :employeeId
    ORDER BY e.createdAt DESC
""")
    List<ExpenseResponse> getEmployeeExpenses(Long employeeId);

    @Query("""
    SELECT new com.expense.ExpenseManagement.dto.ExpenseResponse(
        e.expenseId,
        emp.employeeName,
        e.category,
        e.amount,
        e.description,
        e.expenseDate,
        e.status,
        e.receiptUrl,
        a.adminName,
        e.approvedDate,
        e.remarks
    )
    FROM Expense e
    JOIN e.employee emp
    LEFT JOIN e.approvedBy a
    WHERE e.status = 'SUBMITTED'
""")
    List<ExpenseResponse> getPendingExpenses();
    @Query("""
            SELECT COUNT(e)
            FROM Expense e
            WHERE e.employee.employeeId=:employeeId
            """)
    Long totalExpenses(Integer employeeId);

    @Query("""
        SELECT COUNT(e)
        FROM Expense e
        WHERE e.employee.employeeId=:employeeId
        AND e.status='APPROVED'
        """)
    Long approvedExpenses(Integer employeeId);

    @Query("""
        SELECT COUNT(e)
        FROM Expense e
        WHERE e.employee.employeeId=:employeeId
        AND e.status='SUBMITTED'
        """)
    Long pendingExpenses(Integer employeeId);

    @Query("""
        SELECT COUNT(e)
        FROM Expense e
        WHERE e.employee.employeeId=:employeeId
        AND e.status='REJECTED'
        """)
    Long rejectedExpenses(Integer employeeId);

    @Query("""
        SELECT COALESCE(SUM(e.amount),0)
        FROM Expense e
        WHERE e.employee.employeeId=:employeeId
        AND MONTH(e.expenseDate)=:month
        AND YEAR(e.expenseDate)=:year
        """)
    BigDecimal monthlyExpense(
            Integer employeeId,
            Integer month,
            Integer year
    );

    @Query("""
        SELECT COALESCE(SUM(e.amount),0)
        FROM Expense e
        WHERE e.employee.employeeId=:employeeId
        AND YEAR(e.expenseDate)=:year
        """)
    BigDecimal yearlyExpense(
            Integer employeeId,
            Integer year
    );

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.ExpenseResponse(
            e.expenseId,
            e.employee.employeeName,
            e.category,
            e.amount,
            e.description,
            e.expenseDate,
            e.status,
            e.receiptUrl,
            a.adminName,
            e.approvedDate,
            e.remarks
        )
        FROM Expense e
        LEFT JOIN e.approvedBy a
        WHERE e.employee.employeeId = :employeeId
        ORDER BY e.createdAt DESC
        """)
    Page<ExpenseResponse> recentExpenses(
            Integer employeeId,
            Pageable pageable
    );

    @Query("""
            SELECT COUNT(e)
            FROM Expense e
            """)
    Long getTotalExpenses();

    @Query("""
            SELECT COUNT(e)
            FROM Expense e
            WHERE e.status='APPROVED'
            """)
    Long getApprovedExpenses();

    @Query("""
            SELECT COUNT(e)
            FROM Expense e
            WHERE e.status='REJECTED'
            """)
    Long getRejectedExpenses();

    @Query("""
            SELECT COUNT(e)
            FROM Expense e
            WHERE e.status='SUBMITTED'
            """)
    Long countPendingExpenses();

    @Query("""
            SELECT COALESCE(SUM(e.amount),0)
            FROM Expense e
            """)
    BigDecimal getTotalExpenseAmount();
}
