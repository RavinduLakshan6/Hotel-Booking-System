package com.hotelBooking.Hotel.Reservation.System.Repository;

import com.hotelBooking.Hotel.Reservation.System.Entity.Room;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoomRepository extends MongoRepository<Room, String> {

    @Aggregation("{$group: {_id: '$roomType'}}")
    List<String> findDistinctRoomType();

    @Query("{'bookings':{$size:0}}")
    List<Room> findAvailableRooms();

    List<Room> findByRoomTypeLikeAndIdnotIn(String roomType, List<String> bookedRoomIds);
}
