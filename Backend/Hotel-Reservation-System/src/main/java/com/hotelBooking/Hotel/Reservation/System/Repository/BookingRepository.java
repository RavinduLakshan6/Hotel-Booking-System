package com.hotelBooking.Hotel.Reservation.System.Repository;

import com.hotelBooking.Hotel.Reservation.System.Entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
// import org.springframework.data.mongodb.repository.Query;

// import java.time.LocalDate;
// import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, String> {

  Optional<Booking>findByBookingConfirmationCode(String confirmationCode);

  // @Query("{ 'checkInDate': { $lte: ?1}, 'checkOutDate': { $gte: ?0} }")
  // List<Booking>findBookingsByDateRange(LocalDate checkInDate, LocalDate checkOutDate);


}
