import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUserTie } from '@fortawesome/free-solid-svg-icons'
import { faPeopleRoof } from '@fortawesome/free-solid-svg-icons'
import { faScrewdriverWrench } from '@fortawesome/free-solid-svg-icons'
import { useNavigate } from 'react-router-dom'

function Choice() {
  const navigate=useNavigate();
  return (
    <div className='expense-choice'>
      <h1>Select Mode</h1>
      <div onClick={() => navigate('/employee/login')}>
        <p>Employee</p>
        <FontAwesomeIcon icon={faUserTie}/>
      </div>
      <div onClick={()=>navigate('/admin/login')}>
        <p>Admin</p>
        <FontAwesomeIcon icon={faScrewdriverWrench}/>
      </div>
    </div>
  )
}

export default Choice
