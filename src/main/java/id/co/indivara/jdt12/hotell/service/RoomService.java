package id.co.indivara.jdt12.hotell.service;

import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.repository.RoomRepository;
import id.co.indivara.jdt12.hotell.repository.TransactionBookingRepository;
import id.co.indivara.jdt12.hotell.responsemessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TransactionBookingRepository transactionBookingRepository;
    //Post /rooms
    public ResponseMessage createRoom(Room room) {
        Room rm = roomRepository.save(room);
        return new ResponseMessage(200, "Room berhasil dibuat!!!");
    }
    //GET /rooms
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
    //PUT /room/{id}
    public String updateRoom(Room room, String roomId){
        Room room1 = roomRepository.findById(roomId).get();
        if (Objects.nonNull(room.getRoomType()) && !"".equalsIgnoreCase(room.getRoomType())){
            room1.setRoomType(room.getRoomType());
        }
        return "Tipe Kamar Gagal diupdate";
    }
    //DELETE /room/{id}
    public void deleteRoom(String roomId){

        roomRepository.deleteById(roomId);
    }

}
