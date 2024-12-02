package com.hotelBooking.Hotel.Reservation.System.Repository;

import com.hotelBooking.Hotel.Reservation.System.Entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HotelRepository extends MongoRepository<Hotel, String> {
}
