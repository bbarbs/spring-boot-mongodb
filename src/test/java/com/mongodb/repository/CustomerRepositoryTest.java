package com.mongodb.repository;

import com.mongodb.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repository;

    @Test
    public void testShouldGetCustomerById() {
        // Add customer
        Customer customer = new Customer();
        customer.setEmail("test2@gmail.com");
        customer.setAge(12);
        customer.setFirstname("Test");
        customer.setLastname("Test");
        String customerId = this.repository.save(customer).getId();
        // Get customer by id.
        AtomicReference<Customer> result = new AtomicReference<>(new Customer());
        this.repository.findById(customerId).ifPresent(res -> result.set(res));
        assertNotNull(result.get());
        assertThat(result.get().getEmail()).isEqualTo(customer.getEmail());
    }

    @Test
    public void testShouldGetCustomersByAge() {
        // Add customer.
        Customer customer = new Customer();
        customer.setEmail("test3@gmail.com");
        customer.setAge(1);
        customer.setFirstname("Test");
        customer.setLastname("Test");
        this.repository.save(customer);
        // Get list of customers.
        List<Customer> list = this.repository.findAllByAge(1);
        assertNotNull(list);
        assertThat(list.get(0).getEmail()).isEqualTo(customer.getEmail());
    }
}
