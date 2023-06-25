package id.co.indivara.jdt12.hotell.controller;

import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import id.co.indivara.jdt12.hotell.service.CustomerService;
import id.co.indivara.jdt12.hotell.service.RoomService;
import id.co.indivara.jdt12.hotell.service.TransactionBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionBookingController {
    @Autowired
    private TransactionBookingService transactionBookingService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private RoomService roomService;

    @PostMapping("/transaction")
    public TransactionBooking createBooking(TransactionBooking transactionBooking) throws Exception{
        return transactionBookingService.createBooking(transactionBooking);
    }
    @PutMapping("/transaction/{id}")
    public TransactionBooking updateBooking(TransactionBooking transactionBooking){
        return transactionBooking;
    }

}
