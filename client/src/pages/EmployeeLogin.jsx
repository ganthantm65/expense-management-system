import { faEyeSlash, faLock, faUser } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import React, { useState } from 'react'
import { Link } from 'react-router-dom'  
import '../App.css'

function EmployeeLogin() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordVisible, setPasswordVisible] = useState(false);

    const updateEmail = (event) => {
        setEmail(event.target.value);
    }

    const updatePassword = (event) => {
        setPassword(event.target.value);
    }

    const fetchData = async (email, password) => {
        const userData = { email, password };
        try {
            let url = 'http://localhost:8080/employee/login'; // Corrected URL
            let options = {
                method: "POST",
                headers: { // Fixed the spelling to 'headers'
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            }
            let response = await fetch(url, options);
            let data = await response.json();
            console.log(data);
        } catch (error) {
            console.log(error);
        }
    }

    return (
        <div className='expense-employee-login'>
            <h1>Login</h1>
            <div>
                <FontAwesomeIcon icon={faUser} />
                <input 
                    type="text" 
                    id="expense-employee-email" 
                    value={email}
                    onChange={updateEmail}
                    placeholder='Enter registered email' 
                />
            </div>
            <div>
                <FontAwesomeIcon icon={faLock} />
                <input 
                    type={passwordVisible ? 'text' : 'password'}
                    id="expense-employee-password" 
                    value={password}
                    onChange={updatePassword} 
                    placeholder='Enter password'
                />
                <button 
                    className='password-show'
                    onClick={() => setPasswordVisible(!passwordVisible)}
                >
                    <FontAwesomeIcon icon={passwordVisible ? faEyeSlash : faEyeSlash} />
                </button>
            </div>
            <Link to="/forgot-password">Forgot password</Link>  
            <button onClick={() => fetchData(email, password)}>Login</button>  
            <button>Continue with Google</button>
        </div>
    )
}

export default EmployeeLogin;
