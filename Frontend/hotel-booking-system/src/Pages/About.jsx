import React from 'react'
import Nav from '../Components/Navigation-Bar/Nav';
import Hero from '../Components/Hero/Hero';
import AboutImg from '../Assets/bg4.jpg';
import Footer from '../Components/Footer/Footer';

export default function about() {
  return (
    <div>
        <Nav/>
        <Hero
        cName = "hero-mid"
        heroImg = {AboutImg}
        title = "About Us"
        />

        
        <Footer/>
        
    </div>
  )
}
