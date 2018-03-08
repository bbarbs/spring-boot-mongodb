package com.mongodb.service;

import com.mongodb.model.Customer;
import com.mongodb.repository.CustomerRepository;
import com.mongodb.service.impl.CustomerServiceImpl;
import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.doAnswer;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerServiceImpl service;

    @Mock
    CustomerRepository repository;

    @Test
    public void testShouldGetAllCustomers() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setAge(12);
        // Stub.
        given(this.repository.findAll()).willReturn(Arrays.asList(customer));
        // Observer.
        TestObserver<List<Customer>> observer = new TestObserver<>();
        Maybe<List<Customer>> list = this.service.getAllCustomers();
        list.subscribe(observer);
        // Assert.
        observer.assertValueCount(1);
        observer.assertNoErrors();
        observer.assertValue(Arrays.asList(customer));
    }

    @Test
    public void testShouldAddCustomer() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setAge(12);
        // Stub.
        given(this.repository.save(customer)).willReturn(customer);
        // Observer.
        TestObserver<Customer> observer = new TestObserver<>();
        Maybe<Customer> list = this.service.addCustomer(customer);
        list.subscribe(observer);
        // Assert.
        observer.assertValueCount(1);
        observer.assertNoErrors();
        observer.assertValue(customer);
    }

    @Test
    public void testShouldDeleteCustomerById() {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setAge(12);
        // Stub.
        doAnswer(invocation -> null).when(this.repository).deleteById(Mockito.anyString());
        // Observer.
        TestObserver<Boolean> observer = new TestObserver<>();
        Maybe<Boolean> res = this.service.deleteCustomerById(Mockito.anyString());
        res.subscribe(observer);
        // Assert.
        observer.assertValueCount(1);
        observer.assertComplete();
        observer.assertNoErrors();
    }
}
