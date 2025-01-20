import React, { useState } from "react";
import RoomSearch from "../Room/RoomSearch";
import RoomResult from "../Room/RoomResult";
import Category from '../Category/Category';
import Footer from '../Footer/Footer';
import AcImg from '../../Assets/ac.png';
import MiniBarImg from '../../Assets/mini-bar.png';
import ParkingImg from '../../Assets/parking.png';
import WifiImg from '../../Assets/wifi.png';





const HomePage = () => {

    const [roomSearchResults, setRoomSearchResults] = useState([]);

    // Function to handle search results
    const handleSearchResult = (results) => {
        setRoomSearchResults(results);
    };

    return (
        <div className="home">
            {/* HEADER / BANNER ROOM SECTION */}
            <section>
                <header className="header-banner">
                    <div className="overlay"></div>
                    <div className="animated-texts overlay-content">
                        <h1>Welcome to Beach Side Hotel</h1>
                        <h3>Step into a Heaven of Comfort and Care</h3>
                    </div>
                </header>
            </section>

            {/* SEARCH/FIND AVAILABLE ROOM SECTION */}
            <RoomSearch handleSearchResult={handleSearchResult} />
            <RoomResult roomSearchResults={roomSearchResults} />

            {/* <h4><a className="view-rooms-home" href="/rooms">All Rooms</a></h4> */}


            <Category />


            <h2 className="home-services">Our Services</h2>

            {/* SERVICES SECTION */}
            <section className="service-section"><div className="service-card">
                <img src={AcImg} alt="Air Conditioning" />
                <div className="service-details">
                    <h3 className="service-title">Air Conditioning</h3>
                    <p className="service-description">Stay cool and comfortable throughout your stay with our individually controlled in-room air conditioning.</p>
                </div>
            </div>
                <div className="service-card">
                    <img src={MiniBarImg} alt="Mini Bar" />
                    <div className="service-details">
                        <h3 className="service-title">Mini Bar</h3>
                        <p className="service-description">Enjoy a convenient selection of beverages and snacks stocked in your room's mini bar with no additional cost.</p>
                    </div>
                </div>
                <div className="service-card">
                    <img src={ParkingImg} alt="Parking" />
                    <div className="service-details">
                        <h3 className="service-title">Parking</h3>
                        <p className="service-description">We offer on-site parking for your convenience . Please inquire about valet parking options if available.</p>
                    </div>
                </div>
                <div className="service-card">
                    <img src={WifiImg} alt="WiFi" />
                    <div className="service-details">
                        <h3 className="service-title">WiFi</h3>
                        <p className="service-description">Stay connected throughout your stay with complimentary high-speed Wi-Fi access available in all guest rooms and public areas.</p>
                    </div>
                </div>

            </section>
            {/* AVAILABLE ROOMS SECTION */}
            <section>

            </section>

            <Footer />
        </div>
    );
};

export default HomePage;
