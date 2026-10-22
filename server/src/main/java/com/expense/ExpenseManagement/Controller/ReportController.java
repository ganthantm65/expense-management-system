package com.expense.ExpenseManagement.Controller;

import com.expense.ExpenseManagement.Service.ReportService;
import com.expense.ExpenseManagement.dto.DashboardResponse;
import com.expense.ExpenseManagement.dto.EmployeeDashboardResponse;
import com.expense.ExpenseManagement.dto.MonthlyReportResponse;
import com.expense.ExpenseManagement.dto.YearlyReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
