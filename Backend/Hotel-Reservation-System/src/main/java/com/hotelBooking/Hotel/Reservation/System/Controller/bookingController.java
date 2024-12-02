package com.hotelBooking.Hotel.Reservation.System.Controller;

import com.hotelBooking.Hotel.Reservation.System.DTO.BookingRequest;
import com.hotelBooking.Hotel.Reservation.System.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class bookingController {
    private final BookingService bookingService;

    public bookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookHotel(@RequestBody BookingRequest bookingRequest) {
        try {
            bookingService.bookHotel(bookingRequest);
            return ResponseEntity.ok("Booking successful!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Booking failed: " + e.getMessage());
        }
    }
}
