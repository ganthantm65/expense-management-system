import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

function ForgotPassword() {
  const [email, setEmail] = useState("");
  const [isLoading, setIsLoading] = useState(false);
  const navigate=useNavigate();

  const sendOTP = async () => {
    setIsLoading(true);

    const url = "http://localhost:8080/otp/send";
    const options = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email }),
    };

    try {
      const response = await fetch(url, options);
      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Error in server");
      }

      const data = await response.text();
      if(data=="OTP sent"){
        navigate('/otp-verify')
      }
    } catch (error) {
      console.log(error);
      
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="forgot-password-container">
      <h1 className="forgot-password-heading">Forgot Password</h1>
      <label className="forgot-password-label">Enter your e-mail</label>
      <input
        type="email"
        value={email}
        placeholder="johndoe@gmail.com"
        onChange={(e) => setEmail(e.target.value)}
        className="forgot-password-input"
        aria-label="Enter your email address"
      />
      <button
        className="forgot-password-button"
        onClick={sendOTP}
        disabled={!email.trim() || isLoading}
        aria-busy={isLoading}
      >
        {isLoading ? "Sending..." : "Submit"}
      </button>

    </div>
  );
}

export default ForgotPassword;