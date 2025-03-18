import React from 'react';
import { Link } from 'react-router-dom';

function EmployeeSidebar() {
  return (
    <div className="sidebar">
      <div className="logo">
        <h2>Expense Meter</h2>
      </div>
      <nav>
        <ul>
          <li><Link to="/employee/dashboard">Dashboard</Link></li>
          <li><Link to="/employee/profile">My Profile</Link></li>
          <li><Link to="/employee/expense">Expenses</Link></li>
          <li><Link to="/" onClick={()=>{
            localStorage.clear()
          }}>Logout</Link></li>
        </ul>
      </nav>
    </div>
  );
}

export default EmployeeSidebar;
