package com.hotelBooking.Hotel.Reservation.System.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private String id;

    private String hotelId;
    private String guestName;
    private int rooms;
    private LocalDate bookingDate;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

}
