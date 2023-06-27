package id.co.indivara.jdt12.hotell.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Entity
@Table(name = "trx_bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TransactionBooking extends BaseEntity {
    @Id
    @Column(name = "booking_id")
    @GeneratedValue(generator = "system-uuid")//agar lebih safety, digenerate secara acak/random
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String bookingId;

    //JOIN customer class DENGAN class_id sebagai fk
    @Column(name = "customer_id")
    private String customerId;

    @OneToOne
    @JoinColumn(name = "customer_id", updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

    //JOIN room class DENGAN room_id sebagai fk
    @Column(name = "room_id")
    private String roomId;

    @JoinColumn(name = "room_id", updatable = false, insertable = false)
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;


    @Column(name = "checkin")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant checkIn;

    @Column(name = "checkout")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant checkOut;

    @Column(name = "booking_status")
    private TransactionStatus bookingStatus;

    public enum TransactionStatus{

        ON_BOOKING,
        ON_GOING,
        FINISH

    }
}
