package com.example.hotelsApp.service;

import com.example.hotelsApp.model.Room;

import java.util.List;

public interface RoomService {
    public List<Room> getAllRooms();

    public List<Room> getAllRoomsByHotelId(int hotelId);
}
