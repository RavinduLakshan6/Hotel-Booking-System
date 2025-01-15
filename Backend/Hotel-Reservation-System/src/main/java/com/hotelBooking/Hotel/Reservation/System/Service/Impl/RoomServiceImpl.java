package com.hotelBooking.Hotel.Reservation.System.Service.Impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
import com.hotelBooking.Hotel.Reservation.System.DTO.RoomRequest;
import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import com.hotelBooking.Hotel.Reservation.System.Entity.Room;
import com.hotelBooking.Hotel.Reservation.System.Exception.OurException;
import com.hotelBooking.Hotel.Reservation.System.Repository.BookingRepository;
import com.hotelBooking.Hotel.Reservation.System.Repository.RoomRepository;
import com.hotelBooking.Hotel.Reservation.System.Service.AwsS3Service;
import com.hotelBooking.Hotel.Reservation.System.Service.Interface.RoomService;
import com.hotelBooking.Hotel.Reservation.System.Utils.Utils;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private AwsS3Service awsS3Service;

    @Override
    public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String description) {
        Response response = new Response();

        try {
            String imageUrl = awsS3Service.saveImageToS3(photo);

            Room room = new Room();
            room.setRoomPhotoUrl(imageUrl);
            room.setRoomType(roomType);
            room.setRoomPrice(roomPrice);
            room.setRoomDescription(description);

            Room savedRoom = roomRepository.save(room);
            RoomRequest roomRequest = Utils.mapRoomEntityToRoomRequest(savedRoom);

            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoom(roomRequest);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error saving a room: " + e.getMessage());
        }

        return response;
    }

    @Override
    public List<String> getAllRoomTypes() {
        return roomRepository.findDistinctRoomType();
    }

    @Override
    public Response getAllRooms() {
        Response response = new Response();

        try {
            List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
            List<RoomRequest> roomRequestList = Utils.mapRoomListEntityToRoomListRequest(roomList);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoomList(roomRequestList);
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving rooms: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Response deleteRoom(String roomId) {
        Response response = new Response();

        try {
            roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not Found"));
            roomRepository.deleteById(roomId);
            response.setStatusCode(200);
            response.setMessage("Successful");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error deleting room: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response updateRoom(String roomId, String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
        Response response = new Response();

        try {
            String imageUrl = null;

            if (photo != null && !photo.isEmpty()) {
                imageUrl = awsS3Service.saveImageToS3(photo);
            }

            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            if (roomType != null) room.setRoomType(roomType);
            if (roomPrice != null) room.setRoomPrice(roomPrice);
            if (description != null) room.setRoomDescription(description);
            if (imageUrl != null) room.setRoomPhotoUrl(imageUrl);

            Room updatedRoom = roomRepository.save(room);
            RoomRequest roomRequest = Utils.mapRoomEntityToRoomRequest(updatedRoom);

            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoom(roomRequest);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error updating room: " + e.getMessage());
        }

        return response;
    }

    @Override
    public Response getRoomById(String roomId) {
        Response response = new Response();

        try {
            Room room = roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not found"));
            RoomRequest roomRequest = Utils.mapRoomEntityToRoomRequestPlusBookings(room);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoom(roomRequest);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving room: " + e.getMessage());
        }

        return response;
    }

   @Override
   public Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
       Response response = new Response();

       try {
           List<Room> availableRooms = roomRepository.findAvailableRoomsByDatesAndTypes(checkInDate, checkOutDate, roomType);
           List<RoomRequest> roomRequestsList = Utils.mapRoomListEntityToRoomListRequest(availableRooms);

           response.setStatusCode(200);
           response.setMessage("Successful");
           response.setRoomList(roomRequestsList);
       } catch (Exception e) {
           response.setStatusCode(500);
           response.setMessage("Error rsaving a room " + e.getMessage());
       }
       return response;
   }

    @Override
    public Response getAllAvailableRooms() {
        Response response = new Response();

        try {
            List<Room> roomList = roomRepository.getAllAvailableRooms();
            List<RoomRequest> roomRequestsList = Utils.mapRoomListEntityToRoomListRequest(roomList);
            response.setStatusCode(200);
            response.setMessage("Successful");
            response.setRoomList(roomRequestsList);
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error retrieving available rooms: " + e.getMessage());
        }

        return response;
    }
}
