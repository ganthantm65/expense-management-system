import React from 'react'
import HomePage from '../pages/HomePage'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Choice from '../pages/Choice'
import EmployeeLogin from '../pages/EmployeeLogin'

function LinkRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/choice" element={<Choice />} />
        
        <Route path="/employee">
          <Route path="login" element={<EmployeeLogin />} />
        </Route>
        
      </Routes>
    </BrowserRouter>
  )
}

export default LinkRouter
