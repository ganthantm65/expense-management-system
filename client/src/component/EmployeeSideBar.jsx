import React from 'react';
import { Link } from 'react-router-dom';

function EmployeeSidebar() {
  return (
    <div className="sidebar">
      <div className="logo">
        <h2>Company</h2>
      </div>
      <nav>
        <ul>
          <li>Dashboard</li>
          <li>My Profile</li>
          <li>Expenses</li>
          <li><Link to="/">Logout</Link></li>
        </ul>
      </nav>
    </div>
  );
}

export default EmployeeSidebar;
