package com.hotelBooking.Hotel.Reservation.System.DTO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
    private List<BookingRequest> bookings = new ArrayList<>();
}
