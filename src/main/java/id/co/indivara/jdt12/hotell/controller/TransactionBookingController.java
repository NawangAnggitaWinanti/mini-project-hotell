package id.co.indivara.jdt12.hotell.controller;

import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import id.co.indivara.jdt12.hotell.repository.TransactionBookingRepository;
import id.co.indivara.jdt12.hotell.responsemessage.ResponseMessage;
import id.co.indivara.jdt12.hotell.service.CustomerService;
import id.co.indivara.jdt12.hotell.service.RoomService;
import id.co.indivara.jdt12.hotell.service.TransactionBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionBookingController {
    @Autowired
    private TransactionBookingService transactionBookingService;

    //Bisa diakses Cust dan Admin
    @PostMapping("/transaction")
    public ResponseMessage createBooking(@RequestBody TransactionBooking transactionBooking){
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            transactionBookingService.createBooking(transactionBooking);
            responseMessage.setKode(200);
            responseMessage.setPesan("Booking Berhasil!");
        }catch (Exception ex) {
            responseMessage.setKode(201);
            responseMessage.setPesan("Booking Gagal"+ex.getMessage());
        }
        return responseMessage;
    }
    //History
    @GetMapping("/transaction/all")
    public List<TransactionBooking> getAllTransactionBooking() throws Exception{
        return transactionBookingService.getAllTransactionBooking();
    }

}
