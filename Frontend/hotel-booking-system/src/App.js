import './App.css';
import React, {useEffect} from 'react';
import { useLocation } from 'react-router-dom';
import { BrowserRouter,Routes, Route,Navigate } from 'react-router-dom';
import HomePage from './Components/Home/HomePage';
import Navbar from './Components/Navigation-Bar/Navbar';
import AllRoomsPage from './Components/Room/AllRoomsPage';
import RoomDetailsPage from './Components/Room/RoomDetailsPage';
import ProfilePage from './Components/Profile/ProfilePage';
import EditProfilePage from './Components/Profile/EditProfilePage';
import LoginPage from './Components/Auth/LoginPage';
import RegisterPage from './Components/Auth/RegisterPage';
import FooterComponent from './Components/Footer/FooterComponent';
import EditRoomPage from './Components/Admin/EditRoomPage';
import AdminPage from './Components/Admin/AdminPage';
import ManageRoomPage from './Components/Admin/ManageRoomPage';
import ManageBookingsPage from './Components/Admin/ManageBookingPage';
import AddRoomPage from './Components/Admin/AddRoomPage';
import { ProtectedRoute, AdminRoute } from './Service/guard';


const ScrollToTop = () => {
  const { pathname } = useLocation();

  useEffect(() => {
    window.scrollTo(0, 0);
  }, [pathname]);

  return null;
};


function App() {
  return (
    <BrowserRouter>
    <div className="App">
      <ScrollToTop />
     <Navbar/>
     <div className='content'>
      <Routes>

        <Route exact path='/home' element={<HomePage/>}/>
        <Route path='/rooms' element={<AllRoomsPage/>}/>
        <Route exact path='/login' element={<LoginPage/>}/>
        <Route path='/register' element={<RegisterPage/>}/>


        <Route path='/room-details-book/roomId' element={<ProtectedRoute element={<RoomDetailsPage/>} /> }/>
        <Route path='/profile' element={<ProtectedRoute element={<ProfilePage/>} /> }/>
        <Route path='/edit-profile' element={<ProtectedRoute element={<EditProfilePage/>} />}/>


        <Route path='/admin' element={<AdminRoute element={<AdminPage />} />} />
        <Route path='/admin/manage-rooms' element={<AdminRoute element={<ManageRoomPage />} />} />
        <Route path='/admin/manage-bookings' element={<AdminRoute element={<ManageBookingsPage />} />} />
        <Route path='/admin/add-room' element={<AdminRoute element={<AddRoomPage />} />} />

        <Route path='/admin/edit-room/:roomId' element={<AdminRoute element={<EditRoomPage />} />} />
        {/* <Route path='/admin/edit-booking/:bookingCode' element={<AdminRoute element={<EditRoomPage />} />} /> */}


        <Route path='*' element={<Navigate to="/home"/>} />

      </Routes>
      </div> 

      <FooterComponent />

      </div>
      </BrowserRouter>
   
  );
}

export default App;
