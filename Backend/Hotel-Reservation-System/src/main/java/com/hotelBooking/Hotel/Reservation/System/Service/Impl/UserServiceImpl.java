package com.hotelBooking.Hotel.Reservation.System.Service.Impl;

import com.hotelBooking.Hotel.Reservation.System.DTO.LoginRequest;
import com.hotelBooking.Hotel.Reservation.System.DTO.Response;
import com.hotelBooking.Hotel.Reservation.System.DTO.UserRequest;
import com.hotelBooking.Hotel.Reservation.System.Entity.User;
import com.hotelBooking.Hotel.Reservation.System.Exception.OurException;
import com.hotelBooking.Hotel.Reservation.System.Repository.UserRepository;
import com.hotelBooking.Hotel.Reservation.System.Service.Interface.UserService;
import com.hotelBooking.Hotel.Reservation.System.Utils.JWTUtils;
import com.hotelBooking.Hotel.Reservation.System.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Response register(User user) {

        Response response = new Response();

        try {

            if (user.getRole() == null || user.getRole().isBlank()) {
                user.setRole("USER");
            }

            if (userRepository.existsByEmail(user.getId())) {
                throw new OurException("Email Already Exists");
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);

            UserRequest userRequest = Utils.mapUserEntityToUserRequest(savedUser);


            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(new UserRequest());

        } catch (OurException e) {
            response.setStatusCode(400);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while registering a user: " +e.getMessage());

        }
        return response;
    }

    @Override
    public Response login(LoginRequest loginRequest) {

        Response response = new Response();

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new OurException("USer Not Found"));
            var token = jwtUtils.generateToken(user);

            response.setStatusCode(200);
            response.setMessage("successful");
            response.setToken(token);
            response.setRole(user.getRole());
            response.setExpirationTime("7 days");

        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while logging in a user: " +e.getMessage());

        }
        return response;
    }

    @Override
    public Response getAllUsers() {

        Response response = new Response();

        try {
            List<User> userList = userRepository.findAll();
            List<UserRequest> userDTOList = Utils.mapUserListEntityToUserListRequest(userList);


            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUserList(userDTOList);

        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while getting all users: " +e.getMessage());

        }
        return response;
    }

    @Override
    public Response getUSerBookingHistory(String userId) {

        Response response = new Response();

        try {
            User user = userRepository.findById(userId).orElseThrow(()-> new OurException("User Not Found"));
            UserRequest userRequest= Utils.mapUserEntityToUserRequestPlusUserBookingAndRoom(user);


            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userRequest);

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage( e.getMessage());

        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while getting user booking history: " +e.getMessage());

        }
        return response;
    }

    @Override
    public Response deleteUser(String userId) {

        Response response = new Response();

        try {
            userRepository.findById(userId).orElseThrow(()-> new OurException("User Not Found"));
            userRepository.deleteById(userId);
            response.setStatusCode(200);
            response.setMessage("successful");

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage( e.getMessage());
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while deleting a user: " +e.getMessage());

        }
        return response;
    }

    @Override
    public Response getUserById(String userId) {

        Response response = new Response();

        try {
            User user = userRepository.findById(userId).orElseThrow(()-> new OurException("User Not Found"));
            UserRequest userRequest = Utils.mapUserEntityToUserRequest(user);


            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userRequest);

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage( e.getMessage());
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while getting a user by id : " +e.getMessage());

        }
        return response;
    }

    @Override
    public Response getMyInfo(String email) {

        Response response = new Response();

        try {
            User user = userRepository.findByEmail(email).orElseThrow(()-> new OurException("User Not Found"));
            UserRequest userRequest = Utils.mapUserEntityToUserRequest(user);


            response.setStatusCode(200);
            response.setMessage("successful");
            response.setUser(userRequest);

        }catch (OurException e){
            response.setStatusCode(404);
            response.setMessage( e.getMessage());
        }
        catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage( "Error while getting a user infor: " +e.getMessage());

        }
        return response;
    }

}
