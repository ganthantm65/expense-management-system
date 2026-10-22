import React from "react";
import { useLocation } from "react-router-dom";
import AdminSidebar from "../component/AdminSideBar";
import "../App.css";

function EmployeeSearchProfile() {
    const location = useLocation();
    const employee = location.state?.employee;

    if (!employee) {
        return <div className="error-message">No Employee Data Found</div>;
    }

    const totalExpense = employee.expenses
        ? employee.expenses.reduce((sum, expense) => sum + expense.amount, 0)
        : 0;

    return (
        <div className="employee-profile">
            <AdminSidebar />
            <div className="profile-content">
                <h2 className="profile-title">Employee Profile</h2>
                <div className="profile-card">
                    <h3>{employee.employeeName}</h3>
                    <p><strong>Employee ID:</strong> {employee.employeeId || "N/A"}</p>
                    <p><strong>Email:</strong> {employee.email || "N/A"}</p>
                    <p><strong>Phone:</strong> {employee.phone || "N/A"}</p>
                    <p><strong>Department:</strong> {employee.department || "N/A"}</p>
                    <p><strong>Designation:</strong> {employee.designation || "N/A"}</p>
                    <p><strong>Joining Date:</strong> {employee.joiningDate || "N/A"}</p>
                    <p><strong>Total Expense:</strong> ₹{totalExpense.toFixed(2)}</p>

                    <h3>Expenses</h3>
                    <ul className="expense-list">
                        {employee.expenses && employee.expenses.length > 0 ? (
                            employee.expenses.map((expense, index) => (
                                <li key={index}>
                                    <strong>{expense.category}:</strong> ₹{expense.amount.toFixed(2)} 
                                    <span> ({expense.date || "No Date"})</span>
                                </li>
                            ))
                        ) : (
                            <p>No expenses recorded.</p>
                        )}
                    </ul>
                </div>
            </div>
        </div>
    );
}

export default EmployeeSearchProfile;
