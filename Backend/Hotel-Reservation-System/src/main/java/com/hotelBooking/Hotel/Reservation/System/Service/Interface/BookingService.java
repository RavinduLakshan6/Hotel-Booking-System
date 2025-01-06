package com.hotelBooking.Hotel.Reservation.System.Service.Interface;

import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;

public interface BookingService {
    Response saveBooking(String roomId, String userId, Booking bookingRequest);

   // Response findBookingByConfirmationCode(String confirmationCode);

    Response getAllBookings();

    Response cancelBooking(String bookingId);
}
