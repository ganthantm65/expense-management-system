import React from "react";
import { Pie } from "react-chartjs-2";
import "chart.js/auto";

function AdminPieChart({ employees }) {
    const categoryExpenseMap = {};

    employees.forEach(emp => {
        if (emp.expenses) {
            emp.expenses.forEach(expense => {
                if (categoryExpenseMap[expense.category]) {
                    categoryExpenseMap[expense.category] += expense.amount;
                } else {
                    categoryExpenseMap[expense.category] = expense.amount;
                }
            });
        }
    });

    const categories = Object.keys(categoryExpenseMap);
    const expenseAmounts = Object.values(categoryExpenseMap);

    const chartData = {
        labels: categories,
        datasets: [
            {
                label: "Expense Distribution by Category",
                data: expenseAmounts,
                backgroundColor: [
                    "#4F46E5", "#3B82F6", "#06B6D4", "#10B981", "#F59E0B", "#EF4444", "#8B5CF6"
                ],
                borderWidth: 1,
            }
        ]
    };

    const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                position: "right",
                labels: {
                    color: "#ffffff"
                }
            }
        }
    };

    return (
        <div style={{ width: "400px", height: "400px", margin: "auto" }}>
            <Pie data={chartData} options={chartOptions} />
        </div>
    );
}

export default AdminPieChart;
