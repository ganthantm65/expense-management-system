import React, { useState } from 'react';
import EmployeeSidebar from '../component/EmployeeSideBar';
import EmployeeTopNav from '../component/EmployeeNavBar';
import '../App.css';

function EmployeeExpense() {
  const [employeeData] = useState(() => {
    const storedData = localStorage.getItem("data");
    return storedData ? JSON.parse(storedData) : {};
  });

  const totalExpenses = employeeData.expenses?.reduce((acc, exp) => acc + exp.amount, 0) || 0;
  const averageExpense = employeeData.expenses?.length
    ? totalExpenses / employeeData.expenses.length
    : 0;

  return (
    <div className="dashboard-container">
      <EmployeeSidebar />
      <div className="main-content">
        <EmployeeTopNav />
        <div className="expense-container">
          <h2>Employee Expenses</h2>

          {/* Expense Summary */}
          <div className="expense-metrics">
            <div className="metric-card">
              <h3>Total Expenses</h3>
              <p>₹{totalExpenses.toFixed(2)}</p>
            </div>
            <div className="metric-card">
              <h3>Average Expense</h3>
              <p>₹{averageExpense.toFixed(2)}</p>
            </div>
            <div className="metric-card">
              <h3>Number of Expenses</h3>
              <p>{employeeData.expenses?.length || 0}</p>
            </div>
          </div>

          {/* Scrollable Expense Table */}
          <div className="expense-table-wrapper">
            <h4>Expense Details</h4>
            <div className="expense-table-container">
              <table className="expense-table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Description</th>
                    <th>Amount (₹)</th>
                    <th>Date</th>
                    <th>Category</th>
                  </tr>
                </thead>
                <tbody>
                  {employeeData.expenses?.length ? (
                    employeeData.expenses.map((expense, index) => (
                      <tr key={index}>
                        <td>{index + 1}</td>
                        <td>{expense.description}</td>
                        <td>₹{expense.amount.toFixed(2)}</td>
                        <td>{expense.dateOfExpense ? expense.dateOfExpense : "N/A"}</td>
                        <td>{expense.category ? expense.category : "N/A"}</td>
                      </tr>
                    ))
                  ) : (
                    <tr>
                      <td colSpan="5" className="no-expenses">No expenses recorded</td>
                    </tr>
                  )}
                </tbody>
              </table>
            </div>
          </div>

        </div>
      </div>
    </div>
  );
}

export default EmployeeExpense;
