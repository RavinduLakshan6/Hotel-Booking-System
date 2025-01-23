package com.hotelBooking.Hotel.Reservation.System.Utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

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
   // bookingRequest.setBookingConformationCode(booking.getBookingConformationCode());
    return bookingRequest;
}

public static RoomRequest mapRoomEntityToRoomRequestPlusBookings(Room room){
    RoomRequest roomRequest = new RoomRequest();

    roomRequest.setId(room.getId());
    roomRequest.setRoomType(room.getRoomType());
    roomRequest.setRoomPrice(room.getRoomPrice());
    roomRequest.setRoomPhotoUrl(room.getRoomPhotoUrl());
    roomRequest.setRoomDescription(room.getRoomDescription());

    if (room.getBookings() != null) {
        roomRequest.setBookings(room.getBookings().stream().map(Utils::mapBookingEntityToBookingRequest).collect(Collectors.toList()));
    }
    return roomRequest;
}

public static BookingRequest mapBookingEntityToBookingRequestPlusBookedRooms(Booking booking, boolean mapUser){
    BookingRequest bookingRequest = new BookingRequest();

    bookingRequest.setId(booking.getId());
    bookingRequest.setCheckIDate(booking.getCheckInDate());
    bookingRequest.setCheckOutDate(booking.getCheckOutDate());
    bookingRequest.setTotalGuests(booking.getTotalGuests());
  //  bookingRequest.setBookingConformationCode(booking.getBookingConformationCode());
    
    if(mapUser){
        bookingRequest.setUser(Utils.mapUserEntityToUserRequest(booking.getUser()));
    }

    if(booking.getRoom() != null){
        RoomRequest roomRequest = new RoomRequest();

    roomRequest.setId(booking.getRoom().getId());
    roomRequest.setRoomType(booking.getRoom().getRoomType());
    roomRequest.setRoomPrice(booking.getRoom().getRoomPrice());
    roomRequest.setRoomPhotoUrl(booking.getRoom().getRoomPhotoUrl());
    roomRequest.setRoomDescription(booking.getRoom().getRoomDescription());
    bookingRequest.setRoom(roomRequest);
    }

    return bookingRequest;
}

public static UserRequest mapUserEntityToUserRequestPlusUserBookingAndRoom(User user){
    UserRequest userRequest = new UserRequest();

    userRequest.setId(user.getId());
    userRequest.setName(user.getName());
    userRequest.setEmail(user.getEmail());
    userRequest.setPhoneNumber(user.getPhoneNumber());
    userRequest.setRole(user.getRole());

    if (!user.getBookings().isEmpty()) {
        userRequest.setBookings(user.getBookings().stream().map(booking -> mapBookingEntityToBookingRequestPlusBookedRooms(booking, false)).collect(Collectors.toList()));
    }
    return userRequest;
}

public static List<UserRequest> mapUserListEntityToUserListRequest(List<User> userList){
    return userList.stream().map(Utils::mapUserEntityToUserRequest).collect(Collectors.toList());
}

public static List<RoomRequest> mapRoomListEntityToRoomListRequest(List<Room> roomList){
    return roomList.stream().map(Utils::mapRoomEntityToRoomRequest).collect(Collectors.toList());
}

public static List<BookingRequest> mapBookingListEntityToBookingListRequest(List<Booking> bookingList){
    return bookingList.stream().map(Utils::mapBookingEntityToBookingRequest).collect(Collectors.toList());
}

}


