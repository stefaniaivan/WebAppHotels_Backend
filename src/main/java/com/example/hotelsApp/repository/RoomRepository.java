package com.example.hotelsApp.repository;

import com.example.hotelsApp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    public List<Room> findByHotelIdAndIsAvailableTrue(int hotelId);
}
