package com.abhi.hotel.service.servicelayer;

import com.abhi.hotel.service.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAllHotels();

    Hotel getHotel(String userId);
}
