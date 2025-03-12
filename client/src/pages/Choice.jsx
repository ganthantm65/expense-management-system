import React from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faUserTie } from '@fortawesome/free-solid-svg-icons'
import { faPeopleRoof } from '@fortawesome/free-solid-svg-icons'
import { faScrewdriverWrench } from '@fortawesome/free-solid-svg-icons'

function Choice() {
  return (
    <div className='expense-choice'>
      <h1>Select Mode</h1>
      <div>
        <p>Employee</p>
        <FontAwesomeIcon icon={faUserTie}/>
      </div>
      <div>
        <p>Manager</p>
        <FontAwesomeIcon icon={faPeopleRoof}/>
      </div>
      <div>
        <p>Admin</p>
        <FontAwesomeIcon icon={faScrewdriverWrench}/>
      </div>
    </div>
  )
}

export default Choice
