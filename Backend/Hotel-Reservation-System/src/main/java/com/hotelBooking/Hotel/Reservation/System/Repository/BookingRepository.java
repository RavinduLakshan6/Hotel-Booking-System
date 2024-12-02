package com.hotelBooking.Hotel.Reservation.System.Repository;

import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
