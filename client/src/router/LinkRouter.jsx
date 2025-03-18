import React from 'react'
import HomePage from '../pages/HomePage'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Choice from '../pages/Choice'
import EmployeeLogin from '../pages/EmployeeLogin'
import AdminLogin from '../pages/AdminLogin'
import ForgotPassword from '../pages/ForgotPassword'
import OtpVerifcation from '../pages/OtpVerifcation'
import EmployeeDashboard from '../pages/EmployeeDashBoard'
import { Outlet } from 'react-router-dom'
import OtpVerification from '../pages/OtpVerifcation'
import EmployeExpense from '../pages/EmployeExpense'
import EmployeeProfile from '../pages/EmployeeProfile'

function LinkRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/choice" element={<Choice />} />
        <Route path="/employee/login" element={<EmployeeLogin />} />
        <Route path="/employee/dashboard" element={<EmployeeDashboard />} />
        <Route path="/admin/login" element={<AdminLogin />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />
        <Route path="/otp-verify" element={<OtpVerification/>} />
        <Route path='/employee/expense' element={<EmployeExpense/>}/>
        <Route path='/employee/profile' element={<EmployeeProfile/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default LinkRouter;
