// package com.hotelBooking.Hotel.Reservation.System.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.access.prepost.PreAuthorize;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
// import com.hotelBooking.Hotel.Reservation.System.Service.Interface.UserService;

// @RestController
// @RequestMapping("/booking")
// public class BookingController {
    
//     @Autowired
//     private BookingService bookingService;

//     @PostMapping("/book-room/{roomId}/{userId}")
//     @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
//     public ResponseEntity<Response> saveBookings(@PathVariable String roomId,
//         @PathVariable String userId,
//         @RequestBody Booking bookingRequest){
//             Response response = bookingService.saveBooking(roomId, userId, bookingRequest);
//             return ResponseEntity.status(response.getStatusCode()).body(response);
//     }

//     @GetMapping("/all")
//     @PreAuthorize("hasAuthority('ADMIN')")
//     public ResponseEntity<Response> getAllBookings(){
//         Response response bookingService.getAllBookings();
//         return ResponseEntity.status(response.getStatusCode()).body(response);
//     }

//     @GetMapping("/get-by-confirmation-code/{confirmationCode}")
//     public ResponseEntity<Response> getBookingByConfirmationCode(@PathVariable String confirmationCode){
//         Response response = bookingService.findBookingByConfirmationCode(confirmationCode);
//         return ResponseEntity.status(response.getStatusCode()).body(response);
//     }

//     @DeleteMapping("/cancel/{bookingId}")
//     @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
//     public ResponseEntity<Response> cancelBooking(@PathVariable String bookingId){
//         Response response = bookingService.cancelBooking(bookingId);
//         return ResponseEntity.status(response.getStatusCode()).body(response);
//     }
// }
