import React from "react";
import "E:/projects/expensemanagement/client/src/App.css"; 

const Footer = () => {
  return (
    <footer className="footer">
      <div className="footer-container">
        <div className="footer-logo">
          <h2>Expense Manager</h2>
        </div>
        <nav className="footer-links">
          <a href="#">Home</a>
          <a href="#">About</a>
          <a href="#">Features</a>
          <a href="#">Contact</a>
        </nav>
        <div className="footer-social">
          <a href="#"><i className="fab fa-facebook"></i></a>
          <a href="#"><i className="fab fa-twitter"></i></a>
          <a href="#"><i className="fab fa-instagram"></i></a>
        </div>
      </div>

      <p className="footer-copy">© 2025 Expense Manager. All rights reserved.</p>
    </footer>
  );
};

export default Footer;
