package com.demo.rest.webservices.restfulwebservices.data;

import com.demo.rest.webservices.restfulwebservices.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public List<Customer> findByName(String name);

    public List<Customer> findByAge(int age);
}
