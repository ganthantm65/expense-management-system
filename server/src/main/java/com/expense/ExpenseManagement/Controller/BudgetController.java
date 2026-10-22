package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Service.BudgetService;
import com.expense.ExpenseManagement.dto.BudgetReport;
import com.expense.ExpenseManagement.dto.BudgetRequest;
import com.expense.ExpenseManagement.dto.BudgetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/budgets")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> createBudget(
            @RequestBody BudgetRequest request) {

        return ResponseEntity.ok(
                budgetService.createBudget(request)
        );
    }

    @PutMapping("/{budgetId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> updateBudget(
            @PathVariable Integer budgetId,
            @RequestBody BudgetRequest request) {

        return ResponseEntity.ok(
                budgetService.updatedBudget(request, budgetId)
        );
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<BudgetResponse>> getAllBudgets(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {

        return ResponseEntity.ok(
                budgetService.getAllBudgets(pageable)
        );
    }

    @GetMapping("/{budgetId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BudgetResponse> getBudgetById(
            @PathVariable Integer budgetId) {

        return ResponseEntity.ok(
                budgetService.getBudgetById(budgetId)
        );
    }

    @GetMapping("/year/{year}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BudgetResponse>> getBudgetByYear(
            @PathVariable String year) {

        return ResponseEntity.ok(
                budgetService.findByYear(year)
        );
    }

    @GetMapping("/department/{department}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BudgetResponse>> getBudgetByDepartment(
            @PathVariable String department) {

        return ResponseEntity.ok(
                budgetService.findByBudget(department)
        );
    }

    @GetMapping("/warnings")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> getWarnings() {

        return ResponseEntity.ok(
                budgetService.getWarning()
        );
    }

    @GetMapping("/report")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BudgetReport>> generateReport() {

        return ResponseEntity.ok(
                budgetService.generateReport()
        );
    }
}