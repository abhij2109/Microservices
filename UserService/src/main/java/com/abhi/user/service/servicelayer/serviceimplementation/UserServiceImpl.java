package com.abhi.user.service.servicelayer.serviceimplementation;

import com.abhi.user.service.entity.Hotel;
import com.abhi.user.service.entity.Rating;
import com.abhi.user.service.entity.User;
import com.abhi.user.service.exception.ResourceNotFoundException;
import com.abhi.user.service.externals.HotelService;
import com.abhi.user.service.repository.UserRepository;
import com.abhi.user.service.servicelayer.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String userID = UUID.randomUUID().toString();
        user.setUserID(userID);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        // getting the particular user from database.
        User user = userRepository.findById(userId)
                .orElseThrow(
                        ()-> new ResourceNotFoundException("User not found with given ID."+userId)
                );

        // fetching the ratings given by the above user from RATING-SERVICE
        Rating[] ratingsOfUser =
                restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserID(), Rating[].class);
        assert ratingsOfUser != null;
        List<Rating> ratingsOfUserList = Arrays.stream(ratingsOfUser).toList();
        List<Rating> ratingList = ratingsOfUserList.stream().map(rating -> {

            // Calling of HOTEL-SERVICE to fetch the hotels from given rating.
            ResponseEntity<Hotel> getHotelEntity=
                    restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel=getHotelEntity.getBody();

            // calling via Feign Client
            // Hotel hotel=hotelService.getHotel(rating.getHotelId());
            logger.info("Response Code: {}",getHotelEntity.getStatusCode());

            // Setting the hotel for the particular ratings.
            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }
}
