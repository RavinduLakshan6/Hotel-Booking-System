package com.hotelBooking.Hotel.Reservation.System.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Document(collection = "bookings")
@Data
public class Booking {
    @Id
    private String id;

    @NotNull(message = "Check in date is required")
    private LocalDate checkInDate;


    @NotNull(message = "Check out date is required")
    private LocalDate checkOutDate;

    @Min(value = 1, message = "Total guests should be atleast one")
    private int totalGuests;

    private String bookingConformationCode;

    @DBRef
    private User user;

    @DBRef
    private Room room;

    public void setTotalGuests(int totalGuests){
        this.totalGuests = totalGuests;
    }

    @Override
    public String toString() {
        return "Booking {" +
                "id=" + id + 
                ", checkInDate=" + checkInDate + 
                ", checkOutDate=" + checkOutDate + 
                ", totalGuests=" + totalGuests + 
                ", bookingConformationCode=" + bookingConformationCode + 
                "}";
    }

    
}
