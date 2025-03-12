import React from 'react'
import { useNavigate } from 'react-router-dom'

function TopBar() {
  const navigate=useNavigate()
  return (
    <nav className="topbar-1">
      <h1>Expense Meter</h1>
      <div>
        <p>About Us</p>
        <p>Contact Us</p>
      </div>
      <button onClick={()=>(navigate('/choice'))}>Log in</button>
    </nav>
  )
}

export default TopBar
