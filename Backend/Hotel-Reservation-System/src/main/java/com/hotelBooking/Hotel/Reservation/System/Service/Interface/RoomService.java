package com.hotelBooking.Hotel.Reservation.System.Service.Interface;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hotelBooking.Hotel.Reservation.System.DTO.Response;

public interface RoomService {
    Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description);
    
    List<String> getAllRoomTypes();

    Response getAllRooms();

    Response deleteRoom(String roomId);

    Response updateRoom(String roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo);

    Response getRoomById(String roomId);

    Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

    Response getAllAvailableRooms();
}
