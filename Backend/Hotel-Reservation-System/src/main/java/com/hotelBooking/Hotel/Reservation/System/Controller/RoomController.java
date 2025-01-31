package com.hotelBooking.Hotel.Reservation.System.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
import com.hotelBooking.Hotel.Reservation.System.Service.Interface.BookingService;
import com.hotelBooking.Hotel.Reservation.System.Service.Interface.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private BookingService bookingService;

 @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> addNewRoom(
        @RequestParam(value="photo", required = false) MultipartFile photo,
        @RequestParam(value="roomType", required = false) String roomType,
        @RequestParam(value="roomPrice", required = false) BigDecimal roomPrice,
        @RequestParam(value="roomDescription", required = false) String roomDescription
    ) {
        if (photo == null || photo.isEmpty() || roomType == null || roomType.isBlank() || roomPrice == null || roomType.isBlank()) {
            Response response = new Response();
            response.setStatusCode(400);
            response.setMessage("Please provide value for all fields");
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }
        Response response = roomService.addNewRoom(photo, roomType, roomPrice, roomDescription);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }


    @GetMapping("/all")
    public ResponseEntity<Response> getAllRooms(){
        Response response = roomService.getAllRooms();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/types")
    public List<String> getRoomTypes(){
        return roomService.getAllRoomTypes();
    }

    @GetMapping("/room-by-id/(roomId)")
    public ResponseEntity<Response> getRoomById(@PathVariable String roomId){
        Response response = roomService.getRoomById(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-available-rooms")
    public ResponseEntity<Response> getAvailableRooms(){
        Response response = roomService.getAllAvailableRooms();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

   @GetMapping("/available-room-by-date-and-type")
   public ResponseEntity<Response> getAvailableRoomsByDateAndType(
       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
       @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
       @RequestParam(required = false) String roomType
   ) {
       if( checkInDate == null || roomType == null || roomType.isBlank() || checkOutDate == null){
           Response response = new Response();
           response.setStatusCode(400);
           response.setMessage("Please provide values for all fields");
           return ResponseEntity.status(response.getStatusCode()).body(response);
       }

       Response response = roomService.getAvailableRoomsByDateAndType(checkInDate, checkOutDate, roomType);
       return ResponseEntity.status(response.getStatusCode()).body(response);
   }

    @PutMapping("/update/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateRoom(@PathVariable String roomId,
        @RequestParam(value = "photo",required = false) MultipartFile photo,
        @RequestParam(value = "roomType", required = false) String roomType,
        @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
        @RequestParam(value = "roomDescription", required = false) String roomDescription
    ) {
        Response response = roomService.updateRoom(roomId, roomDescription, roomType, roomPrice, photo);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/delete/{roomId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> deleteRoom(@PathVariable String roomId){
        Response response = roomService.deleteRoom(roomId);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

}
