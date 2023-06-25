package id.co.indivara.jdt12.hotell.repository;

import id.co.indivara.jdt12.hotell.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
