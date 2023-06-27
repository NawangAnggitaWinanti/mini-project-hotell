package id.co.indivara.jdt12.hotell.service;

import id.co.indivara.jdt12.hotell.entity.Customer;
import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import id.co.indivara.jdt12.hotell.repository.CustomerRepository;
import id.co.indivara.jdt12.hotell.repository.RoomRepository;
import id.co.indivara.jdt12.hotell.repository.TransactionBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionBookingService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionBookingRepository transactionBookingRepository;

    @Transactional
    public TransactionBooking createBooking(TransactionBooking transactionBooking) throws Exception {
        TransactionBooking tb= transactionBookingRepository.findById(transactionBooking.getRoomId()).orElseThrow(() -> new Exception("Status Booking Room Tidak Ditemukan!"));
        String statusBooking = transactionBookingRepository.findBookingStatus(tb.getBookingId());
        if (statusBooking != null && statusBooking.equalsIgnoreCase("no")) {
            throw new Exception("Status Room Tidak Dapat ditentukan!!");
        }
        transactionBookingRepository.save(transactionBooking);
        return transactionBooking;
    }
    //Bagian admin
    public TransactionBooking tb(TransactionBooking transactionBooking) throws Exception{
        Customer customer = customerRepository.findById(transactionBooking.getCustomerId()).orElseThrow(()-> new Exception("Customer Tidak Ditemukan!!"));
        transactionBooking.setCustomer(customer);
        transactionBooking.setRoom(room);
        transactionBooking.setCheckIn(transactionBooking.getCheckIn());
        transactionBooking.setCheckOut(transactionBooking.getCheckOut());
        if (Instant.now().isBefore(transactionBooking.getCheckIn())){
            transactionBooking.setBookingStatus(TransactionBooking.TransactionStatus.ON_BOOKING);
        }else {
            transactionBooking.setBookingStatus(TransactionBooking.TransactionStatus.ON_GOING);
        }
        transactionBookingRepository.save(transactionBooking);
        return transactionBooking;
    }

    public List<TransactionBooking> getAllTransactionBooking() throws Exception{
        return (List<TransactionBooking>)transactionBookingRepository.findAll();
    }
}
