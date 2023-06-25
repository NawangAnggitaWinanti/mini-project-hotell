package id.co.indivara.jdt12.hotell.model;

import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class InvoiceHotel {
    private Room room;
    private List<TransactionBooking> transactionBookings;
}
