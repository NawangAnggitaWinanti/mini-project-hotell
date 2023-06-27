package id.co.indivara.jdt12.hotell.controller;


import id.co.indivara.jdt12.hotell.entity.Customer;
import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.model.InvoiceHotel;
import id.co.indivara.jdt12.hotell.repository.RoomRepository;
import id.co.indivara.jdt12.hotell.responsemessage.ResponseMessage;
import id.co.indivara.jdt12.hotell.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private RoomService roomService;

    //GET dapat diakses customer dan admin
    @GetMapping("/rooms")
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }

    //POST, PUT, DELETE hanya dapat diakses admin
    @PostMapping("/admin/rooms")
    public ResponseMessage createRoom(@RequestBody Room room){
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            roomRepository.save(room);
            responseMessage.setKode(200);
            responseMessage.setPesan("Data Berhasil Diinput!!");
        }catch (Exception ex) {
            responseMessage.setKode(201);
            responseMessage.setPesan("Data Gagal Diinput!!!"+ex.getMessage());
        }
        return responseMessage;
    }
    @PutMapping("/admin/{roomId}")
    public ResponseMessage updateRoom(@RequestBody Room room, @PathVariable String roomId){
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Optional<Room> optionalRoom=roomRepository.findById(roomId);
            if(optionalRoom.isPresent()){
                Room r =optionalRoom.get();
                r.setRoomNumber(room.getRoomNumber());
                r.setRoomType(room.getRoomType());
                r.setPrice(room.getPrice());
                r.setDescription(room.getDescription());
                roomRepository.save(r);
                responseMessage.setKode(200);
                responseMessage.setPesan("Kamar Berhasil DiUpdate!!");}
        }catch (Exception ex) {
            responseMessage.setKode(201);
            responseMessage.setPesan("Kamar Gagal DiUpdate!!!"+ex.getMessage());
        }
        return responseMessage;
    }

    @DeleteMapping("/room/delete/{roomId}")
    public ResponseMessage deleteRoom(@PathVariable String roomId){
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            roomService.deleteRoom(roomId);
            responseMessage.setKode(200);
            responseMessage.setPesan("Data Berhasil Dihapus!!!");
        }catch (Exception ex){
            responseMessage.setKode(201);
            responseMessage.setPesan("Data Gagal Dihapus!!!"+ex.getMessage());
        }
        return responseMessage;
    }
    @GetMapping("/historytransaction/{roomId}")
    public InvoiceHotel getInvoice (@PathVariable("roomId") String roomId) throws Exception {
        return roomService.invoiceHotel(roomId);
    }
}
