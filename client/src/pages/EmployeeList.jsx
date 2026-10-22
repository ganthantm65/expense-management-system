import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import AdminSidebar from "../component/AdminSideBar";
import "../App.css";

function EmployeeList() {
    const [employees, setEmployees] = useState([]);
    const [filteredEmployees, setFilteredEmployees] = useState([]);
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
        } catch (error) {
            console.error("Error fetching employees:", error);
        }
    };

    const handleSearch = (event) => {
        const query = event.target.value.toLowerCase();
        setSearchQuery(query);

        if (query === "") {
            setFilteredEmployees(employees);
        } else {
            const filtered = employees.filter(emp =>
                emp.employeeName.toLowerCase().includes(query)
            );
            setFilteredEmployees(filtered);
        }
    };

    const handleViewDetails = (employeeName) => {
        navigate(`/employee/${employeeName}`);
    };

    return (
        <div className="employee-list-page">
            <AdminSidebar />

            <div className="content">
                <h2 className="page-title">Employee List</h2>
                
                {/* Search Bar */}
                <div className="search-container">
                    <input 
                        type="text"
                        placeholder="Search Employee..."
                        value={searchQuery}
                        onChange={handleSearch}
                        className="search-bar"
                    />
                </div>
                
                {/* Employee Table */}
                <div className="employee-table-container">
                    <table className="employee-table">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Designation</th>
                                <th>Total Expense</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredEmployees.length > 0 ? (
                                filteredEmployees.map(emp => {
                                    const totalExpense = emp.expenses
                                        ? emp.expenses.reduce((sum, expense) => sum + expense.amount, 0)
                                        : 0;
                                    return (
                                        <tr key={emp.employeeName}>
                                            <td>{emp.employeeName}</td>
                                            <td>{emp.designation || "N/A"}</td>
                                            <td>₹{totalExpense.toFixed(2)}</td>
                                            <td>
                                                <button className="details-btn" onClick={() => handleViewDetails(emp.employeeName)}>View</button>
                                            </td>
                                        </tr>
                                    );
                                })
                            ) : (
                                <tr>
                                    <td colSpan="4">No employees found.</td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}

export default EmployeeList;
