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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public void updateCustomer(@RequestBody Customer student) {
        repository.save(student);
    }

    @DeleteMapping(path = "/api/customers/{id}")
    public void deleteCustomerById(@PathVariable int id) throws Exception {
        customerService.removeCustomer(id);

    }

    @PostMapping(path = "/api/addCustomer")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer user) throws Exception {
        return ResponseEntity.ok(customerService.addNewCustomer(user));
    }
}
