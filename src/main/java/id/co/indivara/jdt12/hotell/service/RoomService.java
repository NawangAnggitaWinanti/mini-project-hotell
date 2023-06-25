package id.co.indivara.jdt12.hotell.service;

import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    //Post /rooms
    public String createRoom(Room room) {
        Room room1 = roomRepository.save(room);
        if (Objects.nonNull(room1) && room1.getRoomId() != null) {
            return "Room " + room1.getRoomId() + " Berhasil Dibuat";
        } else {
            return "Gagal Insert Room";
        }
    }
    //GET /rooms
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
    //PUT /room/{id}
    public String updateRoom(Room room, Integer roomId){
        Room room1 = roomRepository.findById(roomId).get();
        if (Objects.nonNull(room.getRoomType()) && !"".equalsIgnoreCase(room.getRoomType())){
            room1.setRoomType(room.getRoomType());
        }
        return "Tipe Kamar Gagal diupdate";
    }
    //DELETE /room/{id}
    public void deleteRoom(Integer roomId){

        roomRepository.deleteById(roomId);
    }

}
