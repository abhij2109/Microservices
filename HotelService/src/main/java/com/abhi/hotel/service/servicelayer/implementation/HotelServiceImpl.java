package com.abhi.hotel.service.servicelayer.implementation;

import com.abhi.hotel.service.entity.Hotel;
import com.abhi.hotel.service.exception.ResourceNotFoundException;
import com.abhi.hotel.service.repository.HotelRepository;
import com.abhi.hotel.service.servicelayer.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String id= UUID.randomUUID().toString();
        hotel.setHotelId(id);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String userId) {
        return hotelRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("No hotel with given Id.")
        );
    }
}
