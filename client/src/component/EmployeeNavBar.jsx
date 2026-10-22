import React from 'react';

function EmployeeTopNav() {
  return (
    <div className="top-nav">
      <div className="user-info">
        <span>John Doe</span>
        <span>Employee | HR Department</span>
      </div>
      <div className="profile-pic">
        <img src="https://via.placeholder.com/40" alt="User" />
      </div>
    </div>
  );
}

export default EmployeeTopNav;
