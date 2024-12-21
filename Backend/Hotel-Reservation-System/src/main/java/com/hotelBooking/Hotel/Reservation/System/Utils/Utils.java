package com.hotelBooking.Hotel.Reservation.System.Utils;

import java.security.SecureRandom;

import com.hotelBooking.Hotel.Reservation.System.Controller.bookingController;
import com.hotelBooking.Hotel.Reservation.System.DTO.BookingRequest;
import com.hotelBooking.Hotel.Reservation.System.DTO.RoomRequest;
import com.hotelBooking.Hotel.Reservation.System.DTO.UserRequest;
import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import com.hotelBooking.Hotel.Reservation.System.Entity.Room;
import com.hotelBooking.Hotel.Reservation.System.Entity.User;

public class Utils {

    private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomConfirmationCode(int length) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
            char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
            stringBuilder.append(randomChar); 
        }

        return stringBuilder.toString();
    }


public static UserRequest mapUserEntityToUserRequest(User user){
    UserRequest userRequest = new UserRequest();

    userRequest.setId(user.getId());
    userRequest.setName(user.getName());
    userRequest.setEmail(user.getEmail());
    userRequest.setPhoneNumber(user.getPhoneNumber());
    userRequest.setRole(user.getRole());
    return userRequest;

}

public static RoomRequest mapRoomEntityToRoomRequest(Room room){
    RoomRequest roomRequest = new RoomRequest();

    roomRequest.setId(room.getId());
    roomRequest.setRoomType(room.getRoomType());
    roomRequest.setRoomPrice(room.getRoomPrice());
    roomRequest.setRoomPhotoUrl(room.getRoomPhotoUrl());
    roomRequest.setRoomDescription(room.getRoomDescription());
    return roomRequest;
}

public static BookingRequest mapBookingEntityToBookingRequest(Booking booking){
    BookingRequest bookingRequest = new BookingRequest();

    bookingRequest.setId(booking.getId());
    bookingRequest.setCheckIDate(booking.getCheckInDate());
    bookingRequest.setCheckOutDate(booking.getCheckOutDate());
    bookingRequest.setTotalGuests(booking.getTotalGuests());
    bookingRequest.setBookingConformationCode(booking.getBookingConformationCode());
    return bookingRequest; 
}

}


