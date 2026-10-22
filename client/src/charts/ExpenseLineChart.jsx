import React from "react";
import { Line } from "react-chartjs-2";
import { Chart as ChartJS, CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend } from "chart.js";

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const ExpenseLineChart = ({ expenses }) => {
  const data = {
    labels: expenses.map((expense, index) => `Expense ${index + 1}`),
    datasets: [
      {
        label: "Expense Amount (₹)",
        data: expenses.map((expense) => expense.amount),
        borderColor: "#007bff",
        backgroundColor: "rgba(0, 123, 255, 0.5)",
        tension: 0.4,
        pointRadius: 5,
        pointBackgroundColor: "#007bff",
      },
    ],
  };

  const options = {
    responsive: true,
    plugins: {
      legend: {
        display: true,
        position: "top",
        labels: {
          color: "#ffff",
        },
      },
      title: {
        display: true,
        text: "Employee Expenses Over Time",
        color: "#ffff",
        font: {
          size: 18,
        },
      },
    },
    scales: {
      x: {
        ticks: {
          color: "#ffff",
        },
        grid: {
          color: "rgba(200, 200, 200, 0.2)",
        },
      },
      y: {
        beginAtZero: true,
        ticks: {
          color: "#ffff",
        },
        grid: {
          color: "rgba(200, 200, 200, 0.2)",
        },
      },
    },
  };

  return (
    <div className="line-chart-container">
      <Line data={data} options={options} />
    </div>
  );
};
export default ExpenseLineChart;
