package com.mongodb.web;

import com.mongodb.model.Customer;
import com.mongodb.service.impl.CustomerServiceImpl;
import io.reactivex.Maybe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerServiceImpl service;

    @Test
    public void testShouldGetAllCustomers() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        given(this.service.getAllCustomers()).willReturn(Maybe.just(Arrays.asList(customer)));
        this.mockMvc.perform(
                get("/customers")
                        .contentType(APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testShouldGetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("test@gmail.com");
        given(this.service.getCustomerById(Mockito.anyString())).willReturn(Maybe.just(customer));
        this.mockMvc.perform(
                get("/customers/1")
                        .contentType(APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
