package id.co.indivara.jdt12.hotell.controller;

import id.co.indivara.jdt12.hotell.entity.Customer;
import id.co.indivara.jdt12.hotell.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //POST dan PUT dapat diakses customer dan admin
    @PostMapping("/customers")
    public String createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
    @PutMapping("/customers/{customerId}")
    public String updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId){
        return customerService.updateCustomer(customer, customerId);
    }
    //GET dan DELETE hanya dapat diakses admin
    @GetMapping("/admin/customers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    @DeleteMapping("/admin/delete/{customerId}")
    public String deleteCustomer(@PathVariable Integer customerId){
        customerService.deleteCustomer(customerId);
        return "Data berhasil dihapus!!" ;
    }
}
