package com.example.hotelsApp.service;

import com.example.hotelsApp.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    public List<Hotel> getAllHotels();

    public Hotel saveHotel(Hotel hotel);

    public Optional<Hotel> getHotelById(int id);

    public double lengthOfDegreeLatitude(double lat);

    public double lengthOfDegreeLongitude(double lon);

    public List<Hotel> getHotelsWithinRadius(double latitude, double longitude, double radius);
}
