    package com.expense.ExpenseManagement.Service;

    import com.expense.ExpenseManagement.Model.Budget;
    import com.expense.ExpenseManagement.Repository.BudgetRepo;
    import com.expense.ExpenseManagement.Repository.ExpenseRepo;
    import com.expense.ExpenseManagement.dto.BudgetReport;
    import com.expense.ExpenseManagement.dto.BudgetRequest;
    import com.expense.ExpenseManagement.dto.BudgetResponse;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;

    import java.math.BigDecimal;
    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Map;
    import java.util.Optional;

    @Service
    public class BudgetService {
        @Autowired
        private BudgetRepo budgetRepo;

        @Autowired
        private ExpenseRepo expenseRepo;

        public Map<String,String> createBudget(BudgetRequest request){
            Budget budget=new Budget();

            budget.setDepartment(request.getDepartment());
            budget.setMonth(String.valueOf(request.getMonth()));
            budget.setYear(String.valueOf(request.getYear()));
            budget.setMonthlyLimit(request.getMonthlyLimit());
            budget.setWarningLimit(request.getWarningLimit());
            budget.setCurrentSpent(0.0);
            budget.setCreatedAt(LocalDateTime.now());

            budgetRepo.save(budget);

            return Map.of("message","Budget created successfully");
        }

        public Map<String,String> updatedBudget(BudgetRequest request,Integer budgetId){
            Optional<Budget> budget=budgetRepo.findById(budgetId);

            if(request.getDepartment()!=null){
                budget.get().setDepartment(request.getDepartment());
            }else if (request.getYear()!=null){
                budget.get().setYear(String.valueOf(request.getYear()));
            }else if(request.getWarningLimit()!=null){
                budget.get().setWarningLimit(request.getWarningLimit());
            }else if(request.getMonthlyLimit()!=null){
                budget.get().setMonthlyLimit(request.getMonthlyLimit());
            } else if (request.getMonth()!=null) {
                budget.get().setMonth(String.valueOf(request.getMonth()));
            }

            budgetRepo.save(budget.get());

            return Map.of("message","updated successfully");
        }

        public Page<BudgetResponse> getAllBudgets(Pageable pageable) {

            Page<Budget> budgets = budgetRepo.findAll(pageable);

            return budgets.map(budget -> {

                Double spent = expenseRepo.calculateSpent(
                        budget.getDepartment(),
                        Integer.parseInt(budget.getMonth()),
                        Integer.parseInt(budget.getYear())
                );

                BudgetResponse response = new BudgetResponse();

                response.setBudgetId(budget.getBudgetId());
                response.setDepartment(budget.getDepartment());
                response.setMonthlyLimit(budget.getMonthlyLimit());
                response.setWarningLimit(budget.getWarningLimit());
                response.setMonth(budget.getMonth());
                response.setYear(budget.getYear());

                response.setCurrentSpent(spent);

                return response;
            });
        }
        public List<BudgetResponse> findByYear(String year){
            return budgetRepo.findByYear(year);
        }

        public BudgetResponse getBudgetById(Integer id){
            return budgetRepo.getBudgetById(id).get();
        }

        public List<BudgetResponse> findByBudget(String department){
            return budgetRepo.findByDepartment(department);
        }

        public boolean validateBudget(String department,
                                      Integer month,
                                      Integer year,
                                      Double amount) {

            System.out.println("Department : " + department);
            System.out.println("Month      : " + month);
            System.out.println("Year       : " + year);
            System.out.println("Amount     : " + amount);

            Optional<BudgetResponse> budget =
                    budgetRepo.findBudget(
                            department,
                            String.valueOf(month),
                            String.valueOf(year));

            System.out.println("Budget Found : " + budget.isPresent());

            if (budget.isPresent()) {
                System.out.println("Limit : " + budget.get().getMonthlyLimit());
                System.out.println("Spent : " + budget.get().getCurrentSpent());
            }

            if (budget.isEmpty()) {
                return false;
            }

            BudgetResponse response = budget.get();

            return response.getCurrentSpent() + amount
                    <= response.getMonthlyLimit();
        }

        public Map<String, String> getWarning() {

            List<BudgetResponse> budgets = budgetRepo.getBudgetReport();

            StringBuilder warning = new StringBuilder();

            for (BudgetResponse budget : budgets) {

                double percentage =
                        (budget.getCurrentSpent() * 100)
                                / budget.getMonthlyLimit();

                if (percentage >= budget.getWarningLimit()) {

                    warning.append(budget.getDepartment())
                            .append(" : ")
                            .append(String.format("%.2f", percentage))
                            .append("% utilized\n");
                }
            }

            if (warning.isEmpty()) {
                return Map.of("message", "No budget warnings.");
            }

            return Map.of("message", warning.toString());
        }

        public List<BudgetReport> generateReport() {

            List<BudgetResponse> budgets = budgetRepo.getBudgetReport();

            List<BudgetReport> reports = new ArrayList<>();

            for (BudgetResponse budget : budgets) {

                BudgetReport report = new BudgetReport();

                report.setDepartment(budget.getDepartment());
                report.setMonthlyLimit(budget.getMonthlyLimit());
                report.setSpent(budget.getCurrentSpent());

                double remaining =
                        budget.getMonthlyLimit() - budget.getCurrentSpent();

                report.setRemaining(remaining);

                double utilization = 0;

                if (budget.getMonthlyLimit() > 0) {
                    utilization =
                            (budget.getCurrentSpent() * 100)
                                    / budget.getMonthlyLimit();
                }

                report.setUtilizationPercentage(utilization);

                reports.add(report);
            }

            return reports;
        }

    }
