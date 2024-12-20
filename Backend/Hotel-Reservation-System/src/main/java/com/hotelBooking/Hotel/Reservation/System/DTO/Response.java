package com.hotelBooking.Hotel.Reservation.System.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;

    private UserRequest user;
    private RoomRequest room;
    private BookingRequest booking;
    private List<UserRequest> userList;
    private List<RoomRequest> roomList;
    private List<BookingRequest> bookingList;
}
