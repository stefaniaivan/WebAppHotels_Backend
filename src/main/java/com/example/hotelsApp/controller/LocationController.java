package com.example.hotelsApp.controller;

import com.example.hotelsApp.service.LocationService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocationController {
    @Autowired
    private LocationService locationService;


    @GetMapping("/getLocation")
    public String getLocation(@RequestParam String ip){
        try {
            return locationService.getLocation(ip);
        } catch (IOException | GeoIp2Exception e) {
            return "Error retrieving location: " + e.getMessage();
        }
    }

}
