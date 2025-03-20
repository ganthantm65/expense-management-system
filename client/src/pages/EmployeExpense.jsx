import React, { useState } from 'react';
import EmployeeSidebar from '../component/EmployeeSideBar';
import { Button, Modal, Form } from 'react-bootstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash, faPlus } from '@fortawesome/free-solid-svg-icons';
import '../App.css';

function EmployeeExpense() {
  const [employeeName, setEmployeeName] = useState(() => {
    const storedData = localStorage.getItem("data");
    return storedData ? JSON.parse(storedData).employeeName || "" : "";
  });

  const [employeeData, setEmployeeData] = useState(() => {
    const storedData = localStorage.getItem("data");
    if (storedData) {
      const parsedData = JSON.parse(storedData);
      return {
        expenses: parsedData.expenses.map(expense => ({
          ...expense,
          id: expense.id || Date.now() + Math.random(),
        })),
      };
    }
    return { expenses: [] };
  });

  const [showModal, setShowModal] = useState(false);
  const [newExpense, setNewExpense] = useState({
    category: '',
    amount: '',
    description: '',
    dateOfExpense: '',
  });

  const handleShowModal = () => setShowModal(true);
  const handleCloseModal = () => setShowModal(false);

  const onUpdate = (id, field, value) => {
    const updatedExpenses = employeeData.expenses.map(expense =>
      expense.id === id ? { ...expense, [field]: value } : expense
    );
    setEmployeeData({ expenses: updatedExpenses });

    fetch(`/employee/expense/${employeeName}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ expenses: updatedExpenses }),
    })
      .catch(error => console.error("Error updating expense:", error));
  };

  const onDelete = (id) => {
    const filteredExpenses = employeeData.expenses.filter(expense => expense.id !== id);
    setEmployeeData({ expenses: filteredExpenses });

    fetch(`/employee/expense/${employeeName}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ expenses: filteredExpenses }),
    })
      .catch(error => console.error("Error deleting expense:", error));
  };

  const handleAddExpense = () => {
    if (!newExpense.category || !newExpense.amount || !newExpense.dateOfExpense) {
      alert('Please fill in all required fields.');
      return;
    }

    const expenseToAdd = { 
      ...newExpense, 
      amount: parseFloat(newExpense.amount) 
    };

    fetch(`/employee/expense/${employeeName}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(expenseToAdd),
    })
      .then(response => response.json())
      .then(data => {
        setEmployeeData(prevData => ({ expenses: [...prevData.expenses, data] }));
        setShowModal(false);
        setNewExpense({ category: '', amount: '', description: '', dateOfExpense: '' });
      })
      .catch(error => console.error("Error adding expense:", error));
  };

  return (
    <div className="dashboard-container">
      <EmployeeSidebar />
      <div className="main-content">
        <div className="expense-container">
          <h2>Employee Expenses</h2>
          <Button className="add-expense-btn" onClick={handleShowModal}>
            <FontAwesomeIcon icon={faPlus} /> Add Expense
          </Button>

          <div className="table-wrapper">
            <table className="expense-table">
              <thead>
                <tr>
                  <th>Expense ID</th>
                  <th>Category</th>
                  <th>Amount</th>
                  <th>Description</th>
                  <th>Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {employeeData.expenses.map((expense) => (
                  <tr key={expense.id}>
                    <td>{expense.id}</td>
                    <td>
                      <input
                        type="text"
                        className="input-field"
                        value={expense.category || ''}
                        onChange={(e) => onUpdate(expense.id, 'category', e.target.value)}
                      />
                    </td>
                    <td>
                      <input
                        type="number"
                        className="input-field"
                        value={expense.amount}
                        onChange={(e) => onUpdate(expense.id, 'amount', parseFloat(e.target.value))}
                      />
                    </td>
                    <td>
                      <input
                        type="text"
                        className="input-field"
                        value={expense.description}
                        onChange={(e) => onUpdate(expense.id, 'description', e.target.value)}
                      />
                    </td>
                    <td>
                      <input
                        type="date"
                        className="input-field"
                        value={expense.dateOfExpense || ''}
                        onChange={(e) => onUpdate(expense.id, 'dateOfExpense', e.target.value)}
                      />
                    </td>
                    <td>
                      <button className="delete-button" onClick={() => onDelete(expense.id)}>
                        <FontAwesomeIcon icon={faTrash} />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>

      <Modal show={showModal} onHide={handleCloseModal} centered>
        <Modal.Header closeButton>
          <Modal.Title>Add New Expense</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>Category</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter category"
                value={newExpense.category}
                onChange={(e) => setNewExpense({ ...newExpense, category: e.target.value })}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Amount</Form.Label>
              <Form.Control
                type="number"
                placeholder="Enter amount"
                value={newExpense.amount}
                onChange={(e) => setNewExpense({ ...newExpense, amount: e.target.value })}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Description</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter description"
                value={newExpense.description}
                onChange={(e) => setNewExpense({ ...newExpense, description: e.target.value })}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Date of Expense</Form.Label>
              <Form.Control
                type="date"
                value={newExpense.dateOfExpense}
                onChange={(e) => setNewExpense({ ...newExpense, dateOfExpense: e.target.value })}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>Close</Button>
          <Button variant="primary" onClick={handleAddExpense}>Add Expense</Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default EmployeeExpense;
