package com.hotelBooking.Hotel.Reservation.System.Controller;

import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import com.hotelBooking.Hotel.Reservation.System.Service.Interface.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")

public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/book-room/{roomId}/{userId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> saveBookings(@PathVariable String roomId,
                                                 @PathVariable String userId,
                                                 @RequestBody Booking bookingRequest) {


        Response response = bookingService.saveBooking(roomId, userId, bookingRequest);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllBookings() {
        Response response = bookingService.getAllBookings();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

   @GetMapping("/get-by-confirmation-code/{confirmationCode}")
   public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode) {
       Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
       return ResponseEntity.status(response.getStatusCode()).body(response);
   }

    @DeleteMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Response> cancelBooking(@PathVariable String bookingId) {
        Response response = bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


}
