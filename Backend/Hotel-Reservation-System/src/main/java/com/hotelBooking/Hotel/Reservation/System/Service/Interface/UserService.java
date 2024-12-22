package com.hotelBooking.Hotel.Reservation.System.Service.Interface;

import com.hotelBooking.Hotel.Reservation.System.DTO.LoginRequest;
import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
import com.hotelBooking.Hotel.Reservation.System.Entity.User;

public interface UserService {
    Response register(User user);

    Response login(LoginRequest loginRequest);

    Response getAllUsers();

    Response deleteUser(String userId);

    Response getUserById(String userId);

    Response getMyInfo(String email);

    Response getUSerBookingHistory(String userId);
}
