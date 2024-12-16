import React from 'react'
import './FooterStyle.css';

export default function Footer() {
  return (
    <div className="footer">
        <div className="top">
            <div>
                <h1>Beach Side Hotel</h1>
                <p>Choose your favourite Place</p>
            </div>

            <div>
                <a href="/">
                <i className="fa-brands fa-facebook-square"></i></a>

                <a href="/">
                <i className="fa-brands fa-instagram-square"></i></a>

                <a href="/">
                <i className="fa-brands fa-twitter-square"></i></a>
            </div>
        </div>

        <div className="bottom">
            <div>
                <h4>Bookings</h4>
                <a href="/">Change</a>
                <a href="/">Categories</a>
                <a href="/">License</a>
                <a href="/">All Rooms</a>
            </div>

            <div>
                <h4>Community</h4>
                <a href="/">GitHub</a>
                <a href="/">Issues</a>
                <a href="/">Rooms</a>
                <a href="/">Twitter</a>
            </div>

            <div>
                <h4>Help</h4>
                <a href="/">Support</a>
                <a href="/">Trobleshooting</a>
                <a href="/">Contact Us</a>
            </div>

            <div>
                <h4>Others</h4>
                <a href="/">Terms of Service</a>
                <a href="/">Privacy Policy</a>
                <a href="/">License</a>
            </div>
        </div>
    </div>
  )
}
