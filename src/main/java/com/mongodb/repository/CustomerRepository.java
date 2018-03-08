package com.mongodb.repository;

import com.mongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    /**
     * Get customers by age.
     *
     * @param age
     * @return
     */
    List<Customer> findAllByAge(int age);
}
