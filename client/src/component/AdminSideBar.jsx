import React from "react";
import { Link } from "react-router-dom";
import "../App.css";

function AdminSidebar() {
    return (
        <div className="sidebar">
            <h2>Admin Panel</h2>
            <ul>
                <li><Link to="/admin/dashboard">Dashboard</Link></li>
                <li><Link to="/admin/employees">Employees</Link></li>
                <li><Link to="/admin/profile">Profile</Link></li>
                <li><Link to="/">Logout</Link></li>
            </ul>
        </div>
    );
}

export default AdminSidebar;
