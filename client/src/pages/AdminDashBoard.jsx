import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSearch } from "@fortawesome/free-solid-svg-icons";
import AdminSidebar from "../component/AdminSideBar";
import AdminBarChart from "../charts/AdminBarChart";
import AdminPieChart from "../charts/AdminPieChart";
import "../App.css";

function AdminDashboard() {
    const [employees, setEmployees] = useState([]);
    const [filteredEmployees, setFilteredEmployees] = useState([]);
    const [totalExpenseAmount, setTotalExpenseAmount] = useState(0);
    const [searchQuery, setSearchQuery] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        fetchEmployees();
    }, []);

    const fetchEmployees = async () => {
        try {
            let response = await fetch("http://localhost:8080/employee/getEmployees");
            let data = await response.json();
            setEmployees(data);
            setFilteredEmployees(data);

            const totalAmount = data.reduce((sum, emp) => 
                sum + (emp.expenses ? emp.expenses.reduce((acc, expense) => acc + expense.amount, 0) : 0), 0
            );
            setTotalExpenseAmount(totalAmount);
        } catch (error) {
            console.error("Error fetching employees:", error);
        }
    };

    const handleSearch = (event) => {
        setSearchQuery(event.target.value.toLowerCase());
    };

    const handleSearchRedirect = () => {
        const foundEmployee = employees.find(emp => emp.employeeName.toLowerCase() === searchQuery);
        if (foundEmployee) {
            navigate("/admin/employee-profile", { state: { employee: foundEmployee } });
        } else {
            alert("Employee not found.");
        }
    };

    return (
        <div className="admin-dashboard">
            <AdminSidebar />

            <div className="dashboard-content">
                <h2 className="dashboard-title">Admin Dashboard</h2>

                {/* Top Controls */}
                <div className="top-controls">
                    <div className="search-div">
                        <input 
                            type="text"
                            placeholder="Search Employee..."
                            value={searchQuery}
                            onChange={handleSearch}
                            className="search-bar"
                        />
                        <button onClick={handleSearchRedirect} className="search-btn">
                            <FontAwesomeIcon icon={faSearch} />
                        </button>
                    </div>
                    <button onClick={() => navigate('/admin/addEmployee')} className="add-employee-btn">
                        Add Employee
                    </button>
                </div>

                {/* Stats Cards */}
                <div className="stats-container">
                    <div className="stats-card">
                        <h3>Total Employees</h3>
                        <p>{filteredEmployees.length}</p>
                    </div>
                    <div className="stats-card">
                        <h3>Total Expense Amount</h3>
                        <p>₹{totalExpenseAmount.toFixed(2)}</p>
                    </div>
                </div>

                {/* Charts */}
                <div className="admin-chart">
                    <div className="chart-container">
                        <h3>Employee Expense Distribution</h3>
                        <AdminBarChart employees={filteredEmployees} />
                    </div>
                    <div className="chart-container">
                        <h3>Expense Trend</h3>
                        <AdminPieChart employees={filteredEmployees} />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default AdminDashboard;
