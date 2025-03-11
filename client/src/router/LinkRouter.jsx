import React from 'react'
import HomePage from 'E:/projects/expensemanagement/client/src/pages/HomePage.jsx'
import { BrowserRouter,Routes, Route } from 'react-router-dom'
import Choice from '../pages/Choice'

function LinkRouter() {
  return (
    <BrowserRouter>
        <Routes>
            <Route path='/' element={<HomePage/>}/>
            <Route path='/choice' element={<Choice/>}/>
        </Routes>
    </BrowserRouter>
  )
}

export default LinkRouter
