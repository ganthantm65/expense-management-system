import React, { useState } from "react";
import AdminSidebar from "../component/AdminSideBar";
import "../App.css";

function EmployeeAdd() {
    const [employee, setEmployee] = useState({
        employeeName: "",
        email: "",
        phone: "",
        department: "",
        designation: "",
        joiningDate: "",
        password: ""
    });

    const [message, setMessage] = useState("");

    const handleChange = (e) => {
        setEmployee({ ...employee, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch("http://localhost:8080/employee/register", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(employee),
            });
            const data = await response.json();
            if (response.ok) {
                setMessage("Employee added successfully!");
                setEmployee({
                    employeeName: "",
                    email: "",
                    phone: "",
                    department: "",
                    designation: "",
                    joiningDate: "",
                    password: ""
                });
            } else {
                setMessage(data.message || "Failed to add employee");
            }
        } catch (error) {
            console.error("Error adding employee:", error);
            setMessage("Error adding employee");
        }
    };

    return (
        <div className="employee-add-page">
            <AdminSidebar />
            <div className="content">
                <h2 className="page-title">Add Employee</h2>
                {message && <p className="message">{message}</p>}
                <form onSubmit={handleSubmit} className="employee-form">
                    <input type="text" name="employeeName" placeholder="Employee Name" value={employee.employeeName} onChange={handleChange} required />
                    <input type="email" name="email" placeholder="Email" value={employee.email} onChange={handleChange} required />
                    <input type="text" name="phone" placeholder="Phone" value={employee.phone} onChange={handleChange} required />
                    <input type="text" name="department" placeholder="Department" value={employee.department} onChange={handleChange} required />
                    <input type="text" name="designation" placeholder="Designation" value={employee.designation} onChange={handleChange} required />
                    <input type="date" name="joiningDate" value={employee.joiningDate} onChange={handleChange} required />
                    <input type="password" name="password" placeholder="Password" value={employee.password} onChange={handleChange} required />
                    <button type="submit" className="submit-btn">Add Employee</button>
                </form>
            </div>
        </div>
    );
}

export default EmployeeAdd;
