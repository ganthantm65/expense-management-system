import React, { useState } from "react";
import EmployeeSidebar from "../component/EmployeeSideBar";
import EmployeeTopNav from "../component/EmployeeNavBar";
import "../App.css";
import ExpenseBarChart from "../charts/ExpenseChart";
import ExpenseLineChart from "../charts/ExpenseLineChart";

function EmployeeDashboard() {
  const [employeeData, setEmployeeData] = useState(() => {
    const storedData = localStorage.getItem("data");
    return storedData ? JSON.parse(storedData) : {};
  });

  const expenses = employeeData.expenses || [];

  const totalExpenses = expenses.reduce((sum, expense) => sum + expense.amount, 0).toFixed(2);
  const averageExpense = expenses.length > 0 ? (totalExpenses / expenses.length).toFixed(2) : 0;
  const highExpenseCount = expenses.filter(expense => expense.amount > 100).length;
  const highExpensePercentage = expenses.length > 0 ? ((highExpenseCount / expenses.length) * 100).toFixed(2) : 0;

  return (
    <div className="dashboard-container">
      <EmployeeSidebar />
      <div className="main-content">
        <EmployeeTopNav />
        <div className="employee-dashboard-content">
          <h2>Welcome to the Employee Dashboard</h2>
          <div className="metrics">
            <div className="metric-card">
              <h3>Total Expenses</h3>
              <p>₹{totalExpenses}</p>
            </div>
            <div className="metric-card">
              <h3>Average Expense</h3>
              <p>₹{averageExpense}</p>
            </div>
            <div className="metric-card">
              <h3>High Expense (%)</h3>
              <p>{highExpensePercentage}%</p>
            </div>
          </div>
          <div className="expense-charts">
            <ExpenseBarChart expenses={expenses} />
            <ExpenseLineChart expenses={expenses}/>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EmployeeDashboard;
