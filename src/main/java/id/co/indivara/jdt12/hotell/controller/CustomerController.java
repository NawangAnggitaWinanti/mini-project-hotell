package id.co.indivara.jdt12.hotell.controller;

import id.co.indivara.jdt12.hotell.entity.Customer;
import id.co.indivara.jdt12.hotell.repository.CustomerRepository;
import id.co.indivara.jdt12.hotell.responsemessage.ResponseMessage;
import id.co.indivara.jdt12.hotell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerRepository customerRepository;

    //POST dan PUT dapat diakses customer dan admin
    @PostMapping("/customers")
    public ResponseMessage createCustomer(@RequestBody Customer customer){
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            customerRepository.save(customer);
            responseMessage.setKode(200);
            responseMessage.setPesan("Data Berhasil Diinput!!");
        }catch (Exception ex) {
            responseMessage.setKode(201);
            responseMessage.setPesan("Data Gagal Diinput!!!"+ex.getMessage());
        }
        return responseMessage;
    }
    @PutMapping("/customers/{customerId}")
    public ResponseMessage updateCustomer(@RequestBody Customer customer, @PathVariable String customerId){
        ResponseMessage responseMessage = new ResponseMessage();
        try {
            Optional<Customer> optionalCustomer=customerRepository.findById(customerId);
           if(optionalCustomer.isPresent()){
            Customer c =optionalCustomer.get();
            c.setName(customer.getName());
            c.setNoHp(customer.getNoHp());
            c.setEmail(customer.getEmail());
            c.setNik(customer.getNik());
            c.setAddress(customer.getAddress());
            customerRepository.save(c);
            responseMessage.setKode(200);
            responseMessage.setPesan("Data Berhasil DiUpdate!!");}
        }catch (Exception ex) {
            responseMessage.setKode(201);
            responseMessage.setPesan("Data Gagal DiUpdate!!!"+ex.getMessage());
        }
        return responseMessage;
    }
    //GET dan DELETE hanya dapat diakses admin
    @GetMapping("/admin/customers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @DeleteMapping("/admin/delete/{customerId}")
    public ResponseMessage deleteCustomer(@PathVariable String customerId){
        ResponseMessage responseMessage=new ResponseMessage();
        try{
            customerRepository.deleteById(customerId);
            responseMessage.setKode(200);
            responseMessage.setPesan("Hapus Data Berhasil !!!");
        }catch (Exception ex){
            responseMessage.setKode(201);
            responseMessage.setPesan("Hapus Data Gagal!!! "+ex.getMessage());
        }
        return responseMessage;
    }
}
