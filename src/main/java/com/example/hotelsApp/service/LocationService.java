package com.example.hotelsApp.service;

import com.example.hotelsApp.model.Hotel;
import com.example.hotelsApp.repository.HotelRepository;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    private static DatabaseReader dbReader;

    @PostConstruct
    public void init() throws IOException {
        File database = new File("GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    public static String getLocation(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(ipAddress);

        String country = response.getCountry().getName();
        String city = response.getCity().getName();
        double latitude = response.getLocation().getLatitude();
        double longitude = response.getLocation().getLongitude();

        return "Country: " + country + ", City: " + city + ", Latitude: " + latitude + ", Longitude: " + longitude;
    }


}
