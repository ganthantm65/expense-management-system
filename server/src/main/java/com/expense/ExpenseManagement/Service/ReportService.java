package com.expense.ExpenseManagement.Service;

import com.expense.ExpenseManagement.Repository.AuditRepo;
import com.expense.ExpenseManagement.Repository.BudgetRepo;
import com.expense.ExpenseManagement.Repository.EmployeeRepo;
import com.expense.ExpenseManagement.Repository.ExpenseRepo;
import com.expense.ExpenseManagement.dto.DashboardResponse;
import com.expense.ExpenseManagement.dto.EmployeeDashboardResponse;
import com.expense.ExpenseManagement.dto.ExpenseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReportService {
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private BudgetRepo budgetRepo;

    @Autowired
    private AuditRepo auditRepo;

    public EmployeeDashboardResponse getEmployeeDashboard(Integer employeeId){

        EmployeeDashboardResponse response =
                new EmployeeDashboardResponse();

        response.setTotalExpenses(
                expenseRepo.totalExpenses(employeeId));

        response.setApprovedExpenses(
                expenseRepo.approvedExpenses(employeeId));

        response.setRejectedExpenses(
                expenseRepo.rejectedExpenses(employeeId));

        response.setPendingExpenses(
                expenseRepo.pendingExpenses(employeeId));

        LocalDate today = LocalDate.now();

        response.setMonthlyExpense(
                expenseRepo.monthlyExpense(
                        employeeId,
                        today.getMonthValue(),
                        today.getYear()
                ));

        response.setYearlyExpense(
                expenseRepo.yearlyExpense(
                        employeeId,
                        today.getYear()
                ));

        response.setGst(
                auditRepo.gst(employeeId));

        response.setTds(
                auditRepo.tds(employeeId));

        Page<ExpenseResponse> recent =
                expenseRepo.recentExpenses(
                        employeeId,
                        PageRequest.of(0,5));

        response.setRecentExpenses(
                recent.getContent());

        return response;
    }

    public DashboardResponse getAdminDashboard(){

        DashboardResponse response =
                new DashboardResponse();

        response.setTotalEmployees(
                employeeRepo.getTotalEmployees());

        response.setTotalExpenses(
                expenseRepo.getTotalExpenses());

        response.setApprovedExpenses(
                expenseRepo.getApprovedExpenses());

        response.setRejectedExpenses(
                expenseRepo.getRejectedExpenses());

        response.setPendingExpenses(
                expenseRepo.countPendingExpenses());

        response.setTotalExpenseAmount(
                expenseRepo.getTotalExpenseAmount());

        response.setMonthlyBudget(
                budgetRepo.getTotalBudgetAmount());

        response.setCurrentSpent(
                budgetRepo.getCurrentBudgetUsed());

        response.setRemainingBudget(
                budgetRepo.getRemainingBudget());

        response.setBudgetUtilization(
                response.getCurrentSpent()
                        /
                        response.getMonthlyBudget()
                        *100);

        response.setTotalGST(
                auditRepo.getTotalGST());

        response.setTotalTDS(
                auditRepo.getTotalTDS());

        return response;
    }
}
