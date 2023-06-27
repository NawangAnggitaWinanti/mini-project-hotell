package id.co.indivara.jdt12.hotell.service;

import id.co.indivara.jdt12.hotell.entity.Customer;
import id.co.indivara.jdt12.hotell.repository.CustomerRepository;
import id.co.indivara.jdt12.hotell.responsemessage.ResponseMessage;
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
    public ResponseMessage createCustomer(Customer customer){
        Customer cust = customerRepository.save(customer);
        return new ResponseMessage(200, "Customer berhasil dibuat!!!");
    }
    //GET /customers
    public List<Customer> getAllCustomer() {

        return customerRepository.findAll();
    }
    //PUT /customer/{id}
    public ResponseMessage updateCustomer(Customer customer, String customerId) throws Exception{
        Customer cust = customerRepository.findById(customerId).orElseThrow(()->new Exception("Tidak ada id customer"));
        cust.setName(customer.getName());
        cust.setNoHp(customer.getNoHp());
        cust.setEmail(customer.getEmail());
        cust.setNik(customer.getNik());
        cust.setAddress(customer.getAddress());
        return new ResponseMessage(200,"Berhasil Update");
    }
    //DELETE /customer/{id}
    public void deleteCustomer(String customerId){

        customerRepository.deleteById(customerId);
    }
}

