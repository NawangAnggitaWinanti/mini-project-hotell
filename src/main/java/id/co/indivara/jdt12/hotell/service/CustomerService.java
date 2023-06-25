package id.co.indivara.jdt12.hotell.service;

import id.co.indivara.jdt12.hotell.entity.Customer;
import id.co.indivara.jdt12.hotell.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    //POST: /customers
    //POST: /customers/registrasi
    public String createCustomer(Customer customer) {
        Customer cust = customerRepository.save(customer);
        if (Objects.nonNull(cust) && cust.getCustomerId() != null) {
            return "Customer " + cust.getCustomerId() + " Berhasil Dibuat";
        } else {
            return "Gagal Insert Customer";
        }
    }
    //GET /customers
    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();
    }
    //PUT /customer/{id}
    public String updateCustomer(Customer customer, Integer customerId){
        Customer cust = customerRepository.findById(customerId).get();
        if (Objects.nonNull(customer.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())){
            cust.setEmail(customer.getEmail());
        }
        return "Gagal update";
    }
    //DELETE /customer/{id}
    public void deleteCustomer(Integer customerId){

        customerRepository.deleteById(customerId);
    }
}

