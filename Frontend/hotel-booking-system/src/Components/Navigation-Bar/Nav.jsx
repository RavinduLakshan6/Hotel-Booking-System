import React from 'react'
import './NavStyle.css';

export default function Nav() {
  return (
    <div className="nav">
            <div className="logo">
                <a href="#" ><span>Beach Side</span> Hotel</a>
            </div>

            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">Rooms</a></li>
                <li><a href="#">Category</a></li>
                <li><a href="#">Reviews</a></li>
                <li><a href="#">About Us</a></li>
            </ul> 

            <div className="navBtn">
                <button className="btn">Book Now</button>    
            </div>   
    </div>
  )
}
