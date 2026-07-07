package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.Expense;
import com.expense.ExpenseManagement.dto.ExpenseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
