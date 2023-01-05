package com.demo.rest.webservices.restfulwebservices.service;

import com.demo.rest.webservices.restfulwebservices.data.CustomerRepository;
import com.demo.rest.webservices.restfulwebservices.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private CustomerRepository customerRepositoryMock;

    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customerRepositoryMock = mock(CustomerRepository.class);
        customerService = new CustomerService(customerRepositoryMock);
        customer = Customer.builder()
                .id(111)
                .name("Test Customer")
                .address("Test address")
                .build();
    }


    @DisplayName("should remove a customer when id exists")
    @Test
    public void givenCustomerExists_whenRemove_thenRemove() throws Exception {
        when(customerRepositoryMock.findById(customer.getId())).thenReturn(Optional.ofNullable(customer));
        customerService.removeCustomer(customer.getId());
        verify(customerRepositoryMock, times(1)).deleteById(customer.getId());
    }

    @DisplayName("should add a new customer when id does not exist")
    @Test
    public void givenCustomerDoesNotExists_whenSave_thenSave() throws Exception {
        when(customerRepositoryMock.save(customer)).thenReturn(customer);
        Customer saved=customerService.addNewCustomer(customer);
        assertEquals(saved,customer);
    }

    @DisplayName("should throw an exception when removing a customer that does not exist")
    @Test
    public void givenCustomerNotExists_whenRemove_thenThrowException() throws Exception {
        when(customerRepositoryMock.findById(customer.getId())).thenReturn(null);
        Exception exception = assertThrows(Exception.class, ()->customerService.removeCustomer(customer.getId()));
        assertEquals("Customer does not exist to delete!", exception.getMessage());

    }
}
