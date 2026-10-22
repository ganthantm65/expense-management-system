import React, { useState } from 'react';
import "../App.css"

function OtpVerification() {
  const [otp, setOtp] = useState("");

  const handleOtpChange = (e) => {
    const value = e.target.value;
    if (value.length <= 4) {
      setOtp(value);
    }
  };
  return (
    <div className='otp-verify-container'>
      <h1>OTP Verification</h1>
      <p>Your OTP for changing password has been sent to the email that you have given</p>
      <label>Enter OTP</label>
      <input
        type="number"
        className='otp-verify-otp'
        value={otp}
        onChange={handleOtpChange}
      />
      <button className='otp-verify-btn1'>Submit</button>
    </div>
  );
}

export default OtpVerification;
