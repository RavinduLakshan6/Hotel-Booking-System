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
  };

  return (

    <div className="nav">
            <div className="logo">
                <NavLink to="/home"><span>Beach Side</span> Hotel</NavLink>
            </div>

            <ul>
              <li><NavLink to="/home" activeClass="active">Home</NavLink></li>
              <li><NavLink to="/rooms" activeClass="active">Rooms</NavLink></li>
              <li><NavLink to="/Review" activeClass="active">Reviews</NavLink></li>
              <li><NavLink to="/about" activeClass="active">About</NavLink></li>

              { isUser && <li><NavLink to="/profile" activeClass="active" className="special-link">Profile</NavLink></li>}
              { isAdmin && <li><NavLink to="/admin" activeClass="active" className="special-link">Admin</NavLink></li>}

            </ul>

            <div>
            { isAuthenticated && <button onClick={handleLogout} className="btn">Logout</button>}
              { !isAuthenticated && <NavLink to="/login" activeClass="active"><button className="btn">Login</button></NavLink>}
              { !isAuthenticated && <NavLink to="/register" activeClass="active"><button className="btn">Register</button></NavLink>}
            
            </div>

    </div>
  );
}
