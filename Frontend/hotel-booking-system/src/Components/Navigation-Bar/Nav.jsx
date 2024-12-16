import React from 'react'
import './NavStyle.css';

export default function Nav() {
  return (
    <div className="nav">
            <div className="logo">
                <a href="#" ><span>Beach Side</span> Hotel</a>
            </div>

            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/room">Rooms</a></li>
                <li><a href="/review">Reviews</a></li>
                <li><a href="/about">About Us</a></li>
            </ul> 

            <div className="navBtn">
                <button className="loginBtn">Login</button>  
                <button className="regBtn">Register</button>    
            </div>   
    </div>
  )
}
