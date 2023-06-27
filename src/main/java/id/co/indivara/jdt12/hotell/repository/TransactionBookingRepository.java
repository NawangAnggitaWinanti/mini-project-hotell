package id.co.indivara.jdt12.hotell.repository;

import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionBookingRepository extends JpaRepository<TransactionBooking, String> {
    @Query(value = "SELECT \n" +
            "(CASE WHEN b.booking_status <> 2 THEN 'no' ELSE 'yes' END) AS result\n" +
            "FROM trx_bookings b\n" +
            "WHERE\n" +
            "b.room_id = :roomId \n" +
            "ORDER BY \n" +
            "b.last_update DESC \n" +
            "LIMIT 1",nativeQuery = true)
    String findBookingStatus(@Param("roomId") String roomId);

    List<TransactionBooking> findAllByRoom(Room room);


}

