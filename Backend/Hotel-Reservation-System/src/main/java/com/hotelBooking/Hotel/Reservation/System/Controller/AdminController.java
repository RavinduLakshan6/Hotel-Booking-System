package com.hotelBooking.Hotel.Reservation.System.Controller;

import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import com.hotelBooking.Hotel.Reservation.System.Entity.Room;
import com.hotelBooking.Hotel.Reservation.System.Repository.BookingRepository;
import com.hotelBooking.Hotel.Reservation.System.Repository.RoomRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Add Room
    @PostMapping("/rooms")
    public ResponseEntity<String> addRoom(@RequestBody Room room) {
        roomRepository.save(room);
        return ResponseEntity.ok("Room added successfully");
    }

    // Delete Room
    @DeleteMapping("/rooms/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable String id) {
        roomRepository.deleteById(id);
        return ResponseEntity.ok("Room deleted successfully");
    }

    // Update Room
    @PutMapping("/rooms/{id}")
    public ResponseEntity<String> updateRoom(@PathVariable String id, @RequestBody Room updatedRoom) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isPresent()) {
            Room room = roomOptional.get();
            room.setId(updatedRoom.getId());
            room.setName(updatedRoom.getName());
            room.setLocation(updatedRoom.getLocation());
            room.setTotalRooms(updatedRoom.getTotalRooms());
            room.setAvailableRooms(updatedRoom.getAvailableRooms());
            roomRepository.save(room);
            return ResponseEntity.ok("Room updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
        }
    }

    // Get All Bookings
    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }
}
