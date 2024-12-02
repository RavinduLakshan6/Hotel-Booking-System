package com.hotelBooking.Hotel.Reservation.System.DTO;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookingRequest {
    private String hotelId;
    private String guestName;
    private int rooms;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
