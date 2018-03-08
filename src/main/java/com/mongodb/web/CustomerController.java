package com.mongodb.web;

import com.mongodb.model.Customer;
import com.mongodb.service.CustomerService;
import io.reactivex.Maybe;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    /**
     * Get list of customers.
     *
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "OK", response = Customer.class
            )
    })
    @GetMapping(
            produces = APPLICATION_JSON_VALUE
    )
    public Maybe<List<Customer>> getAllCustomers() {
        return this.service.getAllCustomers();
    }

    /**
     * Add customer data.
     *
     * @param customer
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201, message = "CREATED", response = Customer.class
            )
    })
    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Customer> addCustomer(@RequestBody Customer customer) {
        return this.service.addCustomer(customer);
    }

    /**
     * Get customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "OK", response = Customer.class
            )
    })
    @GetMapping(
            value = "/{customerId}",
            produces = APPLICATION_JSON_VALUE
    )
    public Maybe<Customer> getCustomerById(@PathVariable(name = "customerId") String customerId) {
        return this.service.getCustomerById(customerId);
    }

    /**
     * Update customer by id.
     *
     * @param customerId
     * @param customer
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(
                    code = 201, message = "CREATED", response = Customer.class
            )
    })
    @PutMapping(
            value = "/{customerId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Maybe<Customer> updateCustomerById(@PathVariable(name = "customerId") String customerId, @RequestBody Customer customer) {
        return this.service.updateCustomerById(customerId, customer);
    }

    /**
     * Delete customer by id.
     *
     * @param customerId
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(
                    code = 204, message = "NO CONTENT"
            )
    })
    @DeleteMapping(
            value = "/{customerId}",
            produces = TEXT_PLAIN_VALUE
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteCustomerById(@PathVariable(name = "customerId") String customerId) {
        this.service.deleteCustomerById(customerId).subscribe();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get list of customers by age.
     *
     * @param age
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(
                    code = 200, message = "OK", response = Customer.class
            )
    })
    @GetMapping(
            params = {"age"},
            produces = APPLICATION_JSON_VALUE
    )
    public Maybe<List<Customer>> getCustomersByAge(@RequestParam(name = "age") int age) {
        return this.service.getCustomersByAge(age);
    }
}
