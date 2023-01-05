package com.demo.rest.webservices.restfulwebservices.controller;

import com.demo.rest.webservices.restfulwebservices.data.CustomerRepository;
import com.demo.rest.webservices.restfulwebservices.model.Customer;
import com.demo.rest.webservices.restfulwebservices.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/api/customers")
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @GetMapping(path = "/api/customers/{id}")
    public Optional<Customer> getCustomerById(@PathVariable String id) {
        return repository.findById(Integer.parseInt(id));
    }

    @PutMapping("/api/updateCustomer")
    public void updateCustomer(@RequestBody Customer student){
        repository.save(student);
    }
    @DeleteMapping(path = "/api/students/{id}")
    public void deleteCustomerById(@PathVariable int id) {
        try {
            customerService.removeCustomer(id);
        }
        catch(Exception ex){
            ResponseEntity.status(1001);
        }

    }

    @PostMapping(path = "/api/addCustomer")
    public void addCustomer(@RequestBody Customer user) {
        try {
            customerService.addNewCustomer(user);
        }
        catch(Exception ex){
            ResponseEntity.status(1001);
        }
    }
}
