import './App.css';
import React from 'react';
import { BrowserRouter,Routes, Route,Navigate } from 'react-router-dom';
import HomePage from './Components/Home/HomePage';
import Navbar from './Components/Navigation-Bar/Navbar';
import Footer from './Components/Footer/Footer';
import AllRoomsPage from './Components/Room/AllRoomsPage';
import RoomDetailsPage from './Components/Room/RoomDetailsPage';
import ProfilePage from './Components/Profile/ProfilePage';
import EditProfilePage from './Components/Profile/EditProfilePage';
import LoginPage from './Components/Auth/LoginPage';
import RegisterPage from './Components/Auth/RegisterPage';

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

        <Route path='/login' element={<LoginPage/>}/>
        <Route path='/register' element={<RegisterPage/>}/>

        <Route path='/profile' element={<ProfilePage/>}/>
        <Route path='/edit-profile' element={<EditProfilePage/>}/>

      </Routes>
      </div> 
      <Footer/>

      </div>
      </BrowserRouter>
   
  );
}

export default App;
