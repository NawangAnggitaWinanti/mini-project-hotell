package id.co.indivara.jdt12.hotell.repository;

import id.co.indivara.jdt12.hotell.entity.Room;
import id.co.indivara.jdt12.hotell.entity.TransactionBooking;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface InvoiceRepository extends JpaRepository<TransactionBooking, String>
{
    List<TransactionBooking> findAllByRoom(Room room);
}
