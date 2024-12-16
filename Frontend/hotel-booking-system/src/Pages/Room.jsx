import React from 'react'
import Nav from '../Components/Navigation-Bar/Nav';
import Hero from '../Components/Hero/Hero';
import RoomsImg from '../Assets/bg6.jpg';
import Category from '../Components/Category/Category';
import Footer from '../Components/Footer/Footer';

export default function Room() {
  return (
    <div>

        <Nav/>
        <Hero
        cName = "hero-mid"
        heroImg = {RoomsImg}
        title = "Rooms"
        />

        <Category/>

        <Footer/>

    </div>
  )
}
