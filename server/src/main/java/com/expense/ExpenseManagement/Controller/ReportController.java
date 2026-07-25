package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Service.ReportService;
import com.expense.ExpenseManagement.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/employee/dashboard/{employeeId}")
    public ResponseEntity<EmployeeDashboardResponse> getEmployeeDashboard(
            @PathVariable Integer employeeId) {

        return ResponseEntity.ok(
                reportService.getEmployeeDashboard(employeeId));
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<DashboardResponse> getAdminDashboard() {

        return ResponseEntity.ok(
                reportService.getAdminDashboard());
    }

    @GetMapping("/employee/reports/{employeeId}/monthly")
    public ResponseEntity<MonthlyReportResponse> getEmployeeMonthlyReport(
            @PathVariable Integer employeeId,
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return ResponseEntity.ok(
                reportService.getEmployeeMonthlyReport(
                        employeeId,
                        month,
                        year));
    }

    @GetMapping("/employee/reports/{employeeId}/yearly")
    public ResponseEntity<YearlyReportResponse> getEmployeeYearlyReport(
            @PathVariable Integer employeeId,
            @RequestParam Integer year) {

        return ResponseEntity.ok(
                reportService.getEmployeeYearlyReport(
                        employeeId,
                        year));
    }

    @GetMapping("/admin/reports/monthly")
    public ResponseEntity<AdminMonthlyReport> getAdminMonthlyReport(
            @RequestParam Integer month,
            @RequestParam Integer year) {

        return ResponseEntity.ok(
                reportService.getAdminMonthlyReport(
                        month,
                        year));
    }

    @GetMapping("/admin/reports/yearly")
    public ResponseEntity<AdminYearlyReport> getAdminYearlyReport(
            @RequestParam Integer year) {

        return ResponseEntity.ok(
                reportService.getAdminYearlyReport(year));
    }

    @GetMapping("/admin/reports/budget")
    public ResponseEntity<List<BudgetResponse>> getBudgetReport() {

        return ResponseEntity.ok(
                reportService.getBudgetReport());
    }

    @GetMapping("/admin/reports/tax")
    public ResponseEntity<TaxReport> getTaxReport() {

        return ResponseEntity.ok(
                reportService.getTaxReport());
    }
}