package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Service.ExpenseService;
import com.expense.ExpenseManagement.dto.ExpenseApproval;
import com.expense.ExpenseManagement.dto.ExpenseRequest;
import com.expense.ExpenseManagement.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @PostMapping("/expenses/{employeeId}")
    public ResponseEntity<ExpenseResponse> createExpense(
            @PathVariable Integer employeeId,
            @RequestBody ExpenseRequest expenseRequest
    ) throws Exception {

        return new ResponseEntity<>(
                expenseService.createExpense(employeeId, expenseRequest),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/expenses/{expenseId}")
    public ResponseEntity<Map<String, String>> updateExpense(
            @PathVariable Long expenseId,
            @RequestBody ExpenseRequest expenseRequest
    ) {

        return ResponseEntity.ok(
                expenseService.updateExpense(expenseId, expenseRequest)
        );
    }

    @DeleteMapping("/expenses/{expenseId}")
    public ResponseEntity<Map<String, String>> deleteExpense(
            @PathVariable Long expenseId
    ) throws Exception {

        return ResponseEntity.ok(
                expenseService.deleteExpense(expenseId)
        );
    }

    // Submit Expense
    @PostMapping("/expenses/{expenseId}/submit")
    public ResponseEntity<Map<String, String>> submitExpense(
            @PathVariable Long expenseId
    ) throws Exception {

        return ResponseEntity.ok(
                expenseService.submitExpense(expenseId)
        );
    }

    // Get My Expenses
    @GetMapping("/expenses/my-expenses/{employeeId}")
    public ResponseEntity<List<ExpenseResponse>> getMyExpenses(
            @PathVariable Long employeeId
    ) {

        return ResponseEntity.ok(
                expenseService.getEmployeeExpenses(employeeId)
        );
    }

    @GetMapping("/expenses/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpenseById(
            @PathVariable Long expenseId
    ) throws Exception {

        return ResponseEntity.ok(
                expenseService.getExpenseById(expenseId)
        );
    }

    @GetMapping("/admin/expenses")
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses() {

        return ResponseEntity.ok(
                expenseService.getAllExpenses()
        );
    }

    @GetMapping("/admin/expenses/pending")
    public ResponseEntity<List<ExpenseResponse>> getPendingExpenses() {

        return ResponseEntity.ok(
                expenseService.getPendingExpenses()
        );
    }

    @PutMapping("/admin/expenses/{expenseId}/approve/{adminId}")
    public ResponseEntity<Map<String, String>> approveExpense(
            @PathVariable Long expenseId,
            @PathVariable Long adminId,
            @RequestBody ExpenseApproval expenseApproval
    ) throws Exception {

        return ResponseEntity.ok(
                expenseService.approveExpense(
                        expenseId,
                        adminId,
                        expenseApproval
                )
        );
    }

    @PutMapping("/admin/expenses/{expenseId}/reject/{adminId}")
    public ResponseEntity<Map<String, String>> rejectExpense(
            @PathVariable Long expenseId,
            @PathVariable Long adminId,
            @RequestBody ExpenseApproval expenseApproval
    ) throws Exception {

        return ResponseEntity.ok(
                expenseService.rejectExpense(
                        expenseId,
                        adminId,
                        expenseApproval
                )
        );
    }

}