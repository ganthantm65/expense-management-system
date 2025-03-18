import { faEye, faEyeSlash, faLock, faUser } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useState } from 'react';
import { Link, Navigate, useNavigate } from 'react-router-dom';  
import '../App.css';

function EmployeeLogin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [loading, setLoading] = useState(false);
    const [passwordVisible, setPasswordVisible] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const navigate=useNavigate();

    const updateEmail = (event) => setEmail(event.target.value);
    const updatePassword = (event) => setPassword(event.target.value);

    const fetchData = async () => {
        setLoading(true);
        setErrorMessage('');
        try {
            const response = await fetch('http://localhost:8080/employee/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            const data = await response.json();

            if (!response.ok) {
                throw new Error(data.message || 'Login failed');
            }else{
                localStorage.setItem("data",data)
                navigate('/employee/dashboard')
            }

        } catch (error) {
            setErrorMessage(error.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className='expense-employee-login'>
            <h1>Login</h1>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <div>
                <FontAwesomeIcon icon={faUser} />
                <input 
                    type="text" 
                    value={email}
                    onChange={updateEmail}
                    placeholder='Enter registered email' 
                />
            </div>
            <div>
                <FontAwesomeIcon icon={faLock} />
                <input 
                    type={passwordVisible ? 'text' : 'password'}
                    value={password}
                    onChange={updatePassword} 
                    placeholder='Enter password'
                />
                <button 
                    className='password-show'
                    onClick={() => setPasswordVisible(!passwordVisible)}
                >
                    <FontAwesomeIcon icon={passwordVisible ? faEye : faEyeSlash} />
                </button>
            </div>
            <Link to="/forgot-password">Forgot password</Link>  
            <button onClick={fetchData} disabled={loading}>
                {loading ? "Logging in..." : "Login"}
            </button>  
            <button>Continue with Google</button>
        </div>
    );
}

export default EmployeeLogin;
