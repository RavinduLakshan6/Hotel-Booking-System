import React from 'react'

export default function NavAdmin() {
  return (
    
        <div className="nav">
            <div className="logo">
                <a href="#" ><span>Beach Side</span> Hotel</a>
            </div>

            <ul>
                <li><a href="/adminroom">Manage Rooms</a></li>
                <li><a href="/adminbooking">Manage Bookings</a></li>
            </ul> 

            <div className="navBtn">
                <button className="profileBtn">Profile</button>  
                <button className="logoutBtn">Logout</button>    
            </div>   
    </div>
    
  )
}
