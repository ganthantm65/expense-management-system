import React from "react";
import { Bar } from "react-chartjs-2";
import "chart.js/auto";

function AdminBarChart({ employees }) {
    const chartData = {
        labels: employees.map(emp => emp.employeeName),
        datasets: [
            {
                label: "Total Expense Amount (₹)",
                data: employees.map(emp => 
                    emp.expenses ? emp.expenses.reduce((acc, exp) => acc + exp.amount, 0) : 0
                ),
                backgroundColor: "rgba(255, 255, 255, 0.9)",
                borderColor: "#4F46E5",
                borderWidth: 2,
                borderRadius: 8,
            }
        ]
    };

    const chartOptions = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: {
                labels: {
                    color: "#ffffff"
                }
            }
        },
        scales: {
            x: {
                ticks: {
                    color: "#ffffff"
                }
            },
            y: {
                ticks: {
                    color: "#ffffff"
                }
            }
        }
    };

    return (
        <div style={{ width: "400px", height: "300px", margin: "auto" }}>
            <Bar data={chartData} options={chartOptions} />
        </div>
    );
}

export default AdminBarChart;
