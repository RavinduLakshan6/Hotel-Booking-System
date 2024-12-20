package com.hotelBooking.Hotel.Reservation.System.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingRequest {

    private String id;
    private LocalDate checkIDate;
    private LocalDate checkOutDate;
    private int totalGuests;
    private String bookingConformationCode;
    private UserRequest user;
    private RoomRequest room;
}
