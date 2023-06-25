package id.co.indivara.jdt12.hotell.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer invoiceId;

    //JOIN transactionBooking class dengan bookingId sebagai fk
    @Column(name = "booking_id")
    private Integer bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id",updatable = false, insertable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private TransactionBooking transactionBooking;

    @Column(name = "total")
    private Integer total;
}
