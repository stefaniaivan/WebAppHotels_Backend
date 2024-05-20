package com.example.hotelsApp.service;

import com.example.hotelsApp.model.Room;
import com.example.hotelsApp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllRoomsByHotelId(int hotelId) {
        return roomRepository.findByHotelIdAndIsAvailableTrue(hotelId);
    }
}
