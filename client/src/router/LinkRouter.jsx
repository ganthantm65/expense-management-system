import React from 'react'
import HomePage from '../pages/HomePage'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Choice from '../pages/Choice'
import EmployeeLogin from '../pages/EmployeeLogin'
import AdminLogin from '../pages/AdminLogin'
import ForgotPassword from '../pages/ForgotPassword'
import OtpVerifcation from '../pages/OtpVerifcation'

function LinkRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/choice" element={<Choice />} />
        
        <Route path="/employee">
          <Route path="login" element={<EmployeeLogin />} />
        </Route>
        <Route path='/admin'>
          <Route path='login' element={<AdminLogin/>}/>
        </Route>
        <Route path='/forgot-password' element={<ForgotPassword/>}/>
        <Route path='/otp-verify' element={<OtpVerifcation/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default LinkRouter
