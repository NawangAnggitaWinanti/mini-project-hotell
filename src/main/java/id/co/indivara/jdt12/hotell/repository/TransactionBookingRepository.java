package id.co.indivara.jdt12.hotell.repository;

import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionBookingRepository extends JpaRepository<TransactionBooking, Integer> {
    @Query(value = "SELECT \n" +
            "(CASE WHEN b.booking_status <> 2 THEN 'no' ELSE 'yes' END) AS result\n" +
            "FROM trx_bookings r\n" +
            "WHERE\n" +
            "b.bookingId = :bookingId \n" +
            "ORDER BY \n" +
            "b.LAST_UPDATE_DATE DESC \n" +
            "LIMIT 1",nativeQuery = true)
    String findBookingStatus(@Param("bookingId") Integer bookingId);
}

