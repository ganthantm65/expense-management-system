import React, { useState } from 'react';
import EmployeeSidebar from '../component/EmployeeSideBar';
import EmployeeTopNav from '../component/EmployeeNavBar';
import '../App.css';

function EmployeeProfile() {
  const [employeeData] = useState(() => {
    const storedData = localStorage.getItem("data");
    return storedData ? JSON.parse(storedData) : {};
  });

  const totalExpenses = employeeData.expenses?.reduce((acc, exp) => acc + exp.amount, 0) || 0;

  return (
    <div className="dashboard-container">
      <EmployeeSidebar />
      <div className="main-content">
        <EmployeeTopNav />
        <div className="profile-container">
          <h2>Employee Profile</h2>
          <div className="profile-card">
            <div className="profile-header">
              <div className="profile-avatar">JD</div>
              <h3>{employeeData.employeeName}</h3>
              <p className="designation">{employeeData.designation}</p>
            </div>

            <div className="profile-details">
              <p><strong>Email:</strong> {employeeData.email}</p>
              <p><strong>Employee ID:</strong> {employeeData.id}</p>
              <p><strong>Joining Date:</strong> 12th March 2021</p>
              <p><strong>Department:</strong> {employeeData.department || "Not Assigned"}</p>
              <p><strong>Total Expenses:</strong> ₹{totalExpenses.toFixed(2)}</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EmployeeProfile;
