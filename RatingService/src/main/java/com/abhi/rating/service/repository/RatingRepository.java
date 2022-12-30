package com.abhi.rating.service.repository;

import com.abhi.rating.service.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    List<Rating> findByHotelId(String hotelId);
    List<Rating> findByUserId(String userId);
}