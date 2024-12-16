package com.hotelBooking.Hotel.Reservation.System.Service;

import com.hotelBooking.Hotel.Reservation.System.DTO.BookingRequest;
import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import com.hotelBooking.Hotel.Reservation.System.Entity.Room;
import com.hotelBooking.Hotel.Reservation.System.Repository.BookingRepository;
import com.hotelBooking.Hotel.Reservation.System.Repository.RoomRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository hotelRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository hotelRepository) {
        this.bookingRepository = bookingRepository;
        this.hotelRepository = hotelRepository;
    }

    public void bookHotel(BookingRequest bookingRequest) {
        // Fetch the hotel details
        Room hotel = hotelRepository.findById(bookingRequest.getHotelId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid hotel ID"));

        // Check room availability
        if (hotel.getAvailableRooms() < bookingRequest.getRooms()) {
            throw new IllegalArgumentException("Not enough rooms available");
        }

        // Update room availability
        hotel.setAvailableRooms(hotel.getAvailableRooms() - bookingRequest.getRooms());
        hotelRepository.save(hotel);

        // Save the booking

        Booking booking = new Booking();
        booking.setHotelId(hotel.getId());
        booking.setGuestName(bookingRequest.getGuestName());
        booking.setRooms(bookingRequest.getRooms());
        booking.setBookingDate(LocalDate.now());
        booking.setCheckInDate(bookingRequest.getCheckInDate());

        booking.setCheckOutDate(bookingRequest.getCheckOutDate());
        bookingRepository.save(booking);
    }
}
