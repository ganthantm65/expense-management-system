import React from 'react'
import '../App.css'
import TopBar from '../component/TopBar'
import Footer from '../component/Footer'

function HomePage() {
  return (
    <div className='home-page'>
      <TopBar/>
      <div className='expense-banner'>
        <div className='expense-banner-content'>
          <div className='banner-content'>
            <h1>Spend Smart</h1>
            <h1>Save more</h1>
          </div>
          <div className="banner-img"></div>
        </div>
      </div>
      <div className="expense-about">
        <div className='expense-about-content'>
          <h1>About Us</h1>
          <p>At Expense Meter, we provide innovative and user-friendly expense management solutions to help businesses streamline their financial processes. Our goal is to simplify expense tracking, improve budget control, and offer valuable insights into spending patterns.

Whether you're a small startup or a large enterprise, our platform helps organizations accurately record, categorize, and monitor every transaction. We focus on promoting financial transparency, reducing errors, and enhancing overall efficiency.</p>
        </div>
        <div className="expense-about-img"></div>
      </div>
      <Footer/>
      <div className='expense-features'>

      </div>
    </div>
  )
}

export default HomePage
