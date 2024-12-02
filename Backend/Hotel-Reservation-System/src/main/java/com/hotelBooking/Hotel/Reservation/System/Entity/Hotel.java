package com.hotelBooking.Hotel.Reservation.System.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "hotels")

@Data
public class Hotel {
    @Id
    private String id;

    private String name;
    private String location;
    private int totalRooms;
    private int availableRooms;

    // Getters and Setters
}
