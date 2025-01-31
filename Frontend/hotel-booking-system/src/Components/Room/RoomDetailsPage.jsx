import React, {useState, useEffect} from "react";
import {useParams, useNavigate} from 'react-router-dom';
import ApiService from '../../Service/ApiService';
import DatePicker from 'react-datepicker';
// import { setTime } from "react-datepicker/dist/date_utils";


const RoomDetailsPage = () => {

    const navigate = useNavigate(); //access the navigate function to navigate
    const {roomId} = useParams(); //get roomId from URL parameters
    const [roomDetails, setRoomDetails] = useState(null);
    const [isLoading, setIsLoading] = useState(true);
    const [error, setError] = useState(null);
    const [checkInDate, setCheckInDate] = useState(null);
    const [checkOutDate , setCheckOutDate] = useState(null);
    const [totalGuests, setTotalGuests] = useState(1);
    const [totalPrice, setTotalPrice] = useState(0);
    const [showDatePicker, setShowDatePicker] = useState(false);
    const [userId, setUserId] = useState('');
    const [showMessage, setShowMessaage] = useState(false);
    const [confirmationCode, setConfirmationCode] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    useEffect(() => {
        const fetchData = async () => {
            try {
                setIsLoading(true);
                const response = await ApiService.getRoomById(roomId);
                setRoomDetails(response.room);
                const userProfile = await ApiService.getUserProfile();
                setUserId(userProfile.user.id);
            } catch (error) {
                setError(error.response?.data?.message || error.message);
            } finally {
                setIsLoading(false);
            }
        };
        fetchData();
    }, [roomId]); //re-run the effect when roomId changes

    const handleConfirmBooking = async () => {
        if(!checkInDate || !checkOutDate){
            setErrorMessage('Please select check-in date and check-out date');
            setTimeout(() => setErrorMessage(''), 5000); //clear error msg after 5 seconds
            return;
        }

        if(isNaN(totalGuests) || totalGuests < 1) {
            setErrorMessage('Please enter a valid number for total guests');
            setTimeout(() => setErrorMessage(''), 5000);
            return;
        }

        //calculate total number of days
        const oneDay = 24 * 60 * 60 * 1000;
        const startDate = new Date(checkInDate);
        const endDate = new Date(checkOutDate);
        const totalDays = Math.round(Math.abs((endDate - startDate) / oneDay)) + 1;

        //calculate total price
        const roomPricePerNight = roomDetails.roomPrice;
        const totalPrice = roomPricePerNight * totalDays;

        setTotalPrice(totalPrice);
        setTotalGuests(totalGuests);
    };

    const acceptBooking = async () => {
        try {
            const startDate = new Date(checkInDate);
            const endDate = new Date(checkOutDate);

            //log the original dates for debugging
            console.log("Original Check-in Date:", startDate);
            console.log("Original Check-out Date:", endDate);

            //convert dates to YYYY-MM-DD format, adjusting time zone differences
            const formattedCheckInDate = new Date(startDate.getTime() - (startDate.getTimezoneOffset() * 60000)).toISOString().split('T')[0];
            const formattedCheckOutDate = new Date(endDate.getTime() - (endDate.getTimezoneOffset() * 60000)).toISOString().split('T')[0];

            //log the original dates for debugging
            console.log("Formatted check-in date:", formattedCheckInDate);
            console.log("Formatted cehck-out date:", formattedCheckOutDate);

            //create booking object
            const booking = {
                checkInDate: formattedCheckInDate,
                checkOutDate: formattedCheckOutDate,
                totalGuests: totalGuests
            };
            console.log(booking);
            console.log(checkOutDate);

            //make booking
            const response = await ApiService.bookRoom(roomId,userId,booking);
            if(response.statusCode === 200){
                setConfirmationCode(response.bookingConfirmationCode);
                setShowMessaage(true);

                setTimeout(() => {
                    setShowMessaage(false);
                    navigate('/rooms');
                }, 10000);
            }

        } catch (error) {
            setErrorMessage(error.response?.data?.message || error.message);
            setTimeout(() => setErrorMessage(''), 5000);
        }
    };

    if(isLoading) {
        return <p className="room-detail-loading">Loading Room Details...</p>;
    }

    if(error) {
        return <p className="room-detail-loading">{error}</p>;
    }

    if(!roomDetails) {
        return <p className="room-detail-loading">Room Not Found...</p>;
    }

    const { roomType, roomPrice, roomPhotoUrl, Description, bookings } = roomDetails;

    return (
        <div className="room-details-booking">
            {showMessage && (
                <p className="booking-success-message">
                    Booking Successfull!
                </p>
            )}
            {errorMessage && (
                <p className="error-message">
                    {errorMessage}
                </p>
            )}
            <h2>Room Details</h2>
            <br />
            <img src={roomPhotoUrl} alt={roomType} className="room-details-image" />
            <div className="room-details-info">
                <h3>{roomType}</h3>
                <p>Price: ${roomPrice} / night</p>
                <p> {Description} </p>
            </div>
            {bookings && bookings.lenght > 0 && (
                <div>
                    <h3>Existing Booking Details</h3>
                    <ul className="booking-list">
                        {bookings.map((booking, index) => (
                            <li key={booking.id} className="booking-item" >
                                <span className="booking-number">Booking {index + 1} </span>
                                <span className="booking-text">Check-in: {booking.checkInDate} </span>
                                <span className="booking-text">check-out: {booking.checkOutDate} </span>
                            </li>
                        ))}
                    </ul>
                </div>
            )}
            <div className="booking-info">
                <button className="book-now-button" onClick={() => setShowDatePicker(true)}>Book Now</button>
                <button className="go-back-button" onClick={() => setShowDatePicker(false)}>Go Back</button>
                {showDatePicker && (
                    <div className="date-picker-container">
                        <DatePicker
                            className="detail-search-field"
                            selected={checkInDate}
                            onChange={(date) => setCheckInDate(date)}
                            selectsStart
                            startDate={checkInDate}
                            endDate={checkOutDate}
                            placeholderText="Check-in Date"
                            dateFormat="dd/MM/yyyy"
                        />
                        <DatePicker
                            className="detail-search-field"
                            selected={checkOutDate}
                            onChange={(date) => setCheckOutDate(date)}
                            selectsEnd
                            startDate={checkInDate}
                            endDate={checkOutDate}
                            minDate={checkInDate}
                            placeholderText="Check-out Date"
                            dateFormat="dd/MM/yyyy"
                        />

                        <div className="guest-container">
                            <div className="guest-div">
                                <label>Guests:</label>
                                <input
                                    type="number"
                                    min="1"
                                    value={totalGuests}
                                    onChange={(e) => setTotalGuests(parseInt(e.target.value))}
                                />
                            </div>
                            <button className="confirm-booking" onClick={handleConfirmBooking}>Confirm Booking</button>
                        </div>
                    </div>
                )}
                {totalPrice > 0 && (
                    <div className="total-price">
                        <p>Total Price: ${totalPrice} </p>
                        <p>Total Guests: ${totalGuests} </p>
                        <button onClick={acceptBooking} className="accept-booking">Accept Booking</button>
                    </div>
                )}
            </div>
        </div>
    );

};

export default RoomDetailsPage;