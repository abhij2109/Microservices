package com.abhi.rating.service.servicelayer;

import com.abhi.rating.service.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //getAllRatings
    List<Rating> getAllRatings();

    //getAllByUserId
    List<Rating> getRatingsByUserId(String userId);

    //getAllByHotelId
    List<Rating> getRatingsByHotelId(String hotelId);
}
