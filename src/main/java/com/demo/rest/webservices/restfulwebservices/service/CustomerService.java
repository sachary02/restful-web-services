package com.demo.rest.webservices.restfulwebservices.service;

import com.demo.rest.webservices.restfulwebservices.data.CustomerRepository;
import com.demo.rest.webservices.restfulwebservices.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository){
        this.repository = repository;
    }


    public Customer addNewCustomer(Customer customer) throws Exception{
        Optional<Customer> existingCustomer = repository.findById(customer.getId());
        if (existingCustomer != null && existingCustomer.isPresent()) {
            throw new Exception("Customer ID already exists!");
        }
        return repository.save(customer);
    }

    public void removeCustomer(int id) throws Exception{
        Optional<Customer> existingCustomer = repository.findById(id);
        if (existingCustomer.isEmpty()) {
            throw new Exception("Customer does not exist to delete!");
        }
        repository.deleteById(id);
    }
}
