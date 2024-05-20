package com.example.hotelsApp.controller;

import com.example.hotelsApp.model.Room;
import com.example.hotelsApp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getAll")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
}
