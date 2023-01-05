package com.demo.rest.webservices.restfulwebservices.data;

import com.demo.rest.webservices.restfulwebservices.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    @BeforeEach
    public void setup(){
        customer = Customer.builder()
                .id(111)
                .name("Test Customer")
                .address("Test address")
                .age(50)
                .build();
    }

    @DisplayName("should save a customer")
    @Test
    public void givenCustomerObject_whenSave_thenSaveCustomer() {
        Customer savedCustomer = repository.save(customer);

        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @DisplayName("should list all Customers")
    @Test
    public void getListOfAllCustomers(){
        List<Customer> customers = repository.findAll();

        assertThat(customers.size()).isGreaterThan(0);
    }

    @DisplayName("should find customer by id")
    @Test
    public void givenStudentObject_whenSave_thenFindStudent(){
        Customer savedCustomer = repository.save(customer);
        Optional<Customer> testCustomer = repository.findById(111);

        assertThat(testCustomer).isNotNull();
        assertThat(testCustomer.get().getName()).isEqualTo("Test Customer");
    }

}
