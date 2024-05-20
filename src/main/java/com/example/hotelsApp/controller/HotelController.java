package com.example.hotelsApp.controller;

import com.example.hotelsApp.model.Hotel;
import com.example.hotelsApp.model.Room;
import com.example.hotelsApp.service.HotelService;
import com.example.hotelsApp.service.RoomService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomService roomService;

    @GetMapping("/getById/{id}")
    public Optional<Hotel> getHotelById(@PathVariable int id){
        return hotelService.getHotelById(id);
    }

    @GetMapping("/getAll")
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @PostMapping("/load")
    public String loadHotelsFromJson() {
        String jsonFilePath = "hotels.json";
        try {
            String jsonData = readJSONFile(jsonFilePath);
            JSONArray hotelsArray = new JSONArray(jsonData);

            for (int i = 0; i < hotelsArray.length(); i++) {
                JSONObject hotelJson = hotelsArray.getJSONObject(i);
                Hotel hotel = new Hotel();
                hotel.setId(hotelJson.getInt("id"));
                hotel.setName(hotelJson.getString("name"));
                hotel.setLatitude(hotelJson.getDouble("latitude"));
                hotel.setLongitude(hotelJson.getDouble("longitude"));

                List<Room> roomList = new ArrayList<>();
                JSONArray roomsArray = hotelJson.getJSONArray("rooms");
                for (int j = 0; j < roomsArray.length(); j++) {
                    JSONObject roomJson = roomsArray.getJSONObject(j);
                    Room room = new Room();
                    room.setRoomNumber(roomJson.getInt("roomNumber"));
                    room.setType(roomJson.getInt("type"));
                    room.setPrice(roomJson.getDouble("price"));
                    room.setAvailable(roomJson.getBoolean("isAvailable"));
                    room.setHotel(hotel);
                    roomList.add(room);
                }
                hotel.setRooms(roomList);
                hotelService.saveHotel(hotel);
            }
            return "Data loaded successfully!";
        } catch (IOException e) {
            return "Failed to load data: " + e.getMessage();
        }
    }

    private String readJSONFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    @GetMapping("/getLocationInMetersLat")
    public double getInMetersLat(@RequestParam double lat){
        return hotelService.lengthOfDegreeLatitude(lat);
    }

    @GetMapping("/getLocationInMetersLon")
    public double getInMetersLon(@RequestParam double lon){
        return hotelService.lengthOfDegreeLongitude(lon);
    }

    @GetMapping("/withinRadius")
    public List<Hotel> getHotelsWithinRadius(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        return hotelService.getHotelsWithinRadius(latitude, longitude, radius);
    }

    @GetMapping("/findAvailableRooms/{hotelId}")
    public List<Room> getAvailableRooms(@PathVariable int hotelId) {
        return roomService.getAllRoomsByHotelId(hotelId);
    }
}
