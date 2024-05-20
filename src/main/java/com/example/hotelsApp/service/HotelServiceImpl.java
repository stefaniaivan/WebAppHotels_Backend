package com.example.hotelsApp.service;

import com.example.hotelsApp.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotelsApp.repository.HotelRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public Optional<Hotel> getHotelById(int id) {
        return hotelRepository.findById(id);
    }

    @Override
    public List<Hotel> getHotelsWithinRadius(double userLat, double userLon, double radius) {
        List<Hotel> allHotels = hotelRepository.findAll();
        List<Hotel> hotelsWithinRadius = new ArrayList<>();

        double userLatInMeters = lengthOfDegreeLatitude(userLat);
        double userLonInMeters = lengthOfDegreeLongitude(userLon);

        for (Hotel hotel : allHotels) {
            double hotelLatInMeters = lengthOfDegreeLatitude(hotel.getLatitude());
            double hotelLonInMeters = lengthOfDegreeLongitude(hotel.getLongitude());

            double distance = calculateDistance(userLatInMeters, userLonInMeters, hotelLatInMeters, hotelLonInMeters);

            if (distance <= radius) {
                hotelsWithinRadius.add(hotel);
            }
        }

        return hotelsWithinRadius;
    }

    @Override
    public double lengthOfDegreeLatitude(double latitude) {
        double latRad = Math.toRadians(latitude);
        return 111132.92 - 559.82 * Math.cos(2 * latRad) + 1.175 * Math.cos(4 * latRad) - 0.0023 * Math.cos(6 * latRad);
    }

    @Override
    public double lengthOfDegreeLongitude(double longitude) {
        double lonRad = Math.toRadians(longitude);
        return 111412.84 * Math.cos(lonRad) - 93.5 * Math.cos(3 * lonRad) + 0.118 * Math.cos(5 * lonRad);
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {

        double deltaLat = (lat2 - lat1);
        double deltaLon = (lon2 - lon1);

        return Math.sqrt(deltaLat * deltaLat + deltaLon * deltaLon);
    }

}
