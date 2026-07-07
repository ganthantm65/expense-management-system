package com.expense.ExpenseManagement.Repository;

import com.expense.ExpenseManagement.Model.Budget;
import com.expense.ExpenseManagement.dto.BudgetResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BudgetRepo extends JpaRepository<Budget, Integer> {

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.BudgetResponse(
            b.budgetId,
            b.department,
            b.monthlyLimit,
            b.currentSpent,
            b.warningLimit,
            b.month,
            b.year
        )
        FROM Budget b
    """)
    Page<BudgetResponse> getAllBudgets(Pageable pageable);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.BudgetResponse(
            b.budgetId,
            b.department,
            b.monthlyLimit,
            b.currentSpent,
            b.warningLimit,
            b.month,
            b.year
        )
        FROM Budget b
        WHERE b.budgetId = :budgetId
    """)
    Optional<BudgetResponse> getBudgetById(@Param("budgetId") Integer budgetId);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.BudgetResponse(
            b.budgetId,
            b.department,
            b.monthlyLimit,
            b.currentSpent,
            b.warningLimit,
            b.month,
            b.year
        )
        FROM Budget b
        WHERE b.year = :year
    """)
    List<BudgetResponse> findByYear(@Param("year") String year);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.BudgetResponse(
            b.budgetId,
            b.department,
            b.monthlyLimit,
            b.currentSpent,
            b.warningLimit,
            b.month,
            b.year
        )
        FROM Budget b
        WHERE b.department = :department
    """)
    List<BudgetResponse> findByDepartment(@Param("department") String department);

    @Query("""
        SELECT new com.expense.ExpenseManagement.dto.BudgetResponse(
            b.budgetId,
            b.department,
            b.monthlyLimit,
            b.currentSpent,
            b.warningLimit,
            b.month,
            b.year
        )
        FROM Budget b
        WHERE b.department = :department
          AND b.month = :month
          AND b.year = :year
    """)
    Optional<BudgetResponse> findBudget(
            @Param("department") String department,
            @Param("month") String month,
            @Param("year") String year
    );

    @Query("""
    SELECT new com.expense.ExpenseManagement.dto.BudgetResponse(
        b.budgetId,
        b.department,
        b.monthlyLimit,
        b.currentSpent,
        b.warningLimit,
        b.month,
        b.year
    )
    FROM Budget b
    ORDER BY b.department
""")
    List<BudgetResponse> getBudgetReport();
}