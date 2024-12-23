import './App.css';
import { Route , Routes } from 'react-router-dom';
import Home from './Pages/Home';
import About from './Pages/About';
import Room from './Pages/Room';
import Review from './Pages/Review';
import AdminRoom from './Pages/AdminRoom';
import AdminBooking from './Pages/AdminBooking';

function App() {
  return (

    <div>
    
    <Routes>
      <Route path="/" element={<Home/>} />
      <Route path="/about" element={<About/>} />
      <Route path="/room" element={<Room/>} />
      <Route path="/review" element={<Review/>} />

      <Route path="/adminroom" element={<AdminRoom/>} />
      <Route path="/adminbooking" element={<AdminBooking/>} />
    </Routes>
    

    </div>

  );
}

export default App;
