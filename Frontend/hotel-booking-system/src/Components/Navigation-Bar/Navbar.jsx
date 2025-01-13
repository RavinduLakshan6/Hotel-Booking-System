import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import ApiService from '../../Service/ApiService';
import './NavStyle.css';

export default function Navbar() {
  
  const isAuthenticated = ApiService.isAuthenticated();
  const isAdmin = ApiService.isAdmin();
  const isUser = ApiService.isUser();
  const navigate = useNavigate();

  const handleLogout =() =>{
    const isLogout = window.confirm("Are you sure you really want to Logout ?");
    if(isLogout){
      ApiService.logout();
      navigate("/home");
    }
  }

  return (

    <div className="nav">
            <div className="logo">
                <NavLink to="/home"><span>Beach Side</span> Hotel</NavLink>
            </div>

            <ul>
              <li><NavLink to="/home" activeClass="active">Home</NavLink></li>
              <li><NavLink to="/room" activeClass="active">Rooms</NavLink></li>
              <li><NavLink to="/Review" activeClass="active">Reviews</NavLink></li>
              <li><NavLink to="/about" activeClass="active">About</NavLink></li>

              { isUser && <li><NavLink to="/profile" activeClass="active">Profile</NavLink></li>}
              { isAdmin && <li><NavLink to="/admin" activeClass="active">Admin</NavLink></li>}

              { !isAuthenticated && <li><NavLink to="/login" activeClass="active">Login</NavLink></li>}
              { !isAuthenticated && <li><NavLink to="/register" activeClass="active">Register</NavLink></li>}

              { isAuthenticated && <li onClick={handleLogout}>Logout</li>}
            </ul>

            {/* <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/room">Rooms</a></li>
                <li><a href="/review">Reviews</a></li>
                <li><a href="/about">About Us</a></li>
            </ul> 

            <div className="navBtn">
                <button className="loginBtn">Login</button>  
                <button className="regBtn">Register</button>    
            </div>    */}
    </div>
  )
}
