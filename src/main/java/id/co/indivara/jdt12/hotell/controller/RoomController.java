package id.co.indivara.jdt12.hotell.controller;


import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    //GET dapat diakses customer dan admin
    @GetMapping("/rooms")
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }

    //POST, PUT, DELETE hanya dapat diakses admin
    @PostMapping("/admin/rooms")
    public String createRoom(@RequestBody Room room){
        return roomService.createRoom(room);
    }
    @PutMapping("/admin/{roomId}")
    public String updateRoom(@RequestBody Room room, @PathVariable Integer roomId){
        return roomService.updateRoom(room, roomId);
    }
    @DeleteMapping("/room/delete/{roomId}")
    public String deleteRoom(@PathVariable Integer roomId){
        roomService.deleteRoom(roomId);
        return "Data berhasil dihapus!!" ;
    }
}
