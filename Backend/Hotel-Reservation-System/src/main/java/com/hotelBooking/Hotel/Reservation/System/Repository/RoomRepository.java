package com.hotelBooking.Hotel.Reservation.System.Repository;

import com.hotelBooking.Hotel.Reservation.System.Entity.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
}
