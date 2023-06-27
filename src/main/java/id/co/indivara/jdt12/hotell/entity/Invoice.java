package id.co.indivara.jdt12.hotell.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "trx_invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Invoice extends BaseEntity{
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(generator = "system-uuid")//agar lebih safety, digenerate secara acak/random
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String invoiceId;

    //JOIN transactionBooking class dengan bookingId sebagai fk
    @Column(name = "booking_id")
    private String bookingId;

    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id",updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TransactionBooking transactionBooking;

}
