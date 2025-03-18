import React from 'react';
import EmployeeSidebar from '../component/EmployeeSideBar';
import EmployeeTopNav from '../component/EmployeeNavBar';
import '../App.css';

function EmployeeDashboard() {
  return (
    <div className="dashboard-container">
      <EmployeeSidebar />
      <div className="main-content">
        <EmployeeTopNav />
        <div className="dashboard-content">
          <h2>Welcome to the Employee Dashboard</h2>
          <div className="metrics">
            <div className="metric-card">
              <h3>Total Tasks</h3>
              <p>25</p>
            </div>
            <div className="metric-card">
              <h3>Completed Tasks</h3>
              <p>20</p>
            </div>
            <div className="metric-card">
              <h3>Pending Tasks</h3>
              <p>5</p>
            </div>
            <div className="metric-card">
              <h3>Total Expenses</h3>
              <p>$200</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default EmployeeDashboard;
