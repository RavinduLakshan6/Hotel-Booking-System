import './App.css';
import React from 'react';
import { BrowserRouter,Routes, Route,Navigate } from 'react-router-dom';
import HomePage from './Components/Home/HomePage';
import Navbar from './Components/Navigation-Bar/Navbar';
import Footer from './Components/Footer/Footer';
import AllRoomsPage from './Components/Room/AllRoomsPage';
import RoomDetailsPage from './Components/Room/RoomDetailsPage';

function App() {
  return (
    <BrowserRouter>
    <div className="App">
     <Navbar/>
     <div className='content'>
      <Routes>
        <Route exact path='/home' element={<HomePage/>}/>
        <Route exact path='/rooms' element={<AllRoomsPage/>}/>
        <Route path='/room-details-book/roomId' element={<RoomDetailsPage/>}/>
      </Routes>
      </div> 
      <Footer/>

      </div>
      </BrowserRouter>
   
  );
}

export default App;
