package com.mongodb.service.impl;

import com.mongodb.model.Customer;
import com.mongodb.repository.CustomerRepository;
import com.mongodb.service.CustomerService;
import io.reactivex.Maybe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository repository;

    @Override
    public Maybe<List<Customer>> getAllCustomers() {
        return Maybe.fromCallable(() -> {
            List<Customer> customers = this.repository.findAll();
            return customers;
        });
    }

    @Override
    public Maybe<Customer> addCustomer(Customer customer) {
        return Maybe.fromCallable(() -> {
            Customer c = this.repository.save(customer);
            return c;
        });
    }

    @Override
    public Maybe<Customer> getCustomerById(String customerId) {
        return Maybe.fromCallable(() -> {
            AtomicReference<Customer> c = new AtomicReference<>(new Customer());
            this.repository.findById(customerId).ifPresent(customer -> c.set(customer));
            return c.get();
        });
    }

    @Override
    public Maybe<Customer> updateCustomerById(String customerId, Customer customer) {
        return Maybe.fromCallable(() -> {
            customer.setId(customerId);
            Customer c = this.repository.save(customer);
            return c;
        });
    }

    @Override
    public Maybe<Boolean> deleteCustomerById(String customerId) {
        return Maybe.fromCallable(() -> {
            repository.deleteById(customerId);
            return true;
        });
    }

    @Override
    public Maybe<List<Customer>> getCustomersByAge(int age) {
        return Maybe.fromCallable(() -> {
            List<Customer> list = this.repository.findAllByAge(age);
            return list;
        });
    }
}
