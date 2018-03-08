package com.mongodb.service;

import com.mongodb.model.Customer;
import io.reactivex.Maybe;

import java.util.List;

public interface CustomerService {

    /**
     * Get all list of customers.
     *
     * @return
     */
    Maybe<List<Customer>> getAllCustomers();

    /**
     * Add new customer.
     *
     * @param customer
     * @return
     */
    Maybe<Customer> addCustomer(Customer customer);

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    Maybe<Customer> getCustomerById(String customerId);

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    Maybe<Customer> updateCustomerById(String customerId, Customer customer);

    /**
     * Delete customer by id.
     *
     * @param customerId
     */
    Maybe<Boolean> deleteCustomerById(String customerId);

    /**
     * Get list of customers by age.
     *
     * @param age
     * @return
     */
    Maybe<List<Customer>> getCustomersByAge(int age);
}
