package com.company.customerdataservice.controller;


import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private CustomerRepository mockRepo;
    @InjectMocks
    private CustomerController customerController;
    private MockMvc mockMvc;

    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void shouldAddCustomer() throws Exception {
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu", "chi@gmail.com",
                "Google", "4155703748", "Fenimore", "Brooklyn",
                "New York City", "New York", "11225", "Nigeria");

        // The repo.save() method will return the same customer object with an ID assigned
        when(mockRepo.save(any(Customer.class))).thenReturn(customer);

        // Perform the POST request
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{...JSON content for customer...}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer.getId()))
                .andExpect(jsonPath("$.firstName").value(customer.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customer.getLastName()))
                .andExpect(jsonPath("$.email").value(customer.getEmail()))
                .andExpect(jsonPath("$.company").value(customer.getCompany()))
                .andExpect(jsonPath("$.address1").value(customer.getAddress1()))
                .andExpect(jsonPath("$.address2").value(customer.getAddress2()))
                .andExpect(jsonPath("$.city").value(customer.getCity()))
                .andExpect(jsonPath("$.state").value(customer.getState()))
                .andExpect(jsonPath("$.postalCode").value(customer.getPostalCode()))
                .andExpect(jsonPath("$.country").value(customer.getCountry()));


        // Verify that the repo.save() method was called once
        verify(mockRepo, times(1)).save(any(Customer.class));
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225", "Nigeria");

        when(mockRepo.findById(1)).thenReturn(customer);
        when(mockRepo.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{...JSON content for updated customer...}"))
                .andExpect(status().isNoContent());
        
        verify(mockRepo, times(1)).findById(1);
        verify(mockRepo, times(1)).save(any(Customer.class));
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu", "chi@gmail.com",
                "Google", "4155703748", "Fenimore", "Brooklyn", "New York City",
                "New York", "11225", "Nigeria");

        // Simulate the customer already existing in the database
        when(mockRepo.findById(1)).thenReturn(customer);
        doNothing().when(mockRepo).deleteById(1);

        // Perform the DELETE request
        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isNoContent());

        // Verify that the repo.findById() and repo.deleteById() methods were called
        verify(mockRepo, times(1)).findById(1);
        verify(mockRepo, times(1)).deleteById(1);
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldGetCustomers() throws Exception {
        Customer customer1 = new Customer(1, "Chiamaka", "Udechukwu", "chi@gmail.com",
                "Google", "4155703748", "Fenimore", "Brooklyn", "New York City",
                "New York", "11225", "Nigeria");

        Customer customer2 = new Customer(2, "Chi", "Udee", "chiude@gmail.com",
                "Uber", "4155703748", "Fenimore", "Brooklyn", "New York City",
                "New York", "11225", "Nigeria");

        List<Customer> customers = Arrays.asList(customer1, customer2);
        when(mockRepo.findAll()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(customers.size()))
                .andExpect(jsonPath("$[0].id").value(customer1.getId()))
                .andExpect(jsonPath("$[0].firstName").value(customer1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(customer1.getLastName()))
                .andExpect(jsonPath("$[1].id").value(customer2.getId()))
                .andExpect(jsonPath("$[1].firstName").value(customer2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(customer2.getLastName()));

        verify(mockRepo, times(1)).findAll();
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldGetCustomerById() throws Exception {
        Customer customer1 = new Customer(1, "Chiamaka", "Udechukwu", "chi@gmail.com", "Google", "4155703748", "Fenimore", "Brooklyn", "New York City", "New York", "11225", "Nigeria");

        when(mockRepo.findById(1)).thenReturn(customer1);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(customer1.getId()))
                .andExpect(jsonPath("$.firstName").value(customer1.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customer1.getLastName()))
                .andExpect(jsonPath("$.email").value(customer1.getEmail()))
                .andExpect(jsonPath("$.company").value(customer1.getCompany()))
                .andExpect(jsonPath("$.address1").value(customer1.getAddress1()))
                .andExpect(jsonPath("$.address2").value(customer1.getAddress2()))
                .andExpect(jsonPath("$.city").value(customer1.getCity()))
                .andExpect(jsonPath("$.state").value(customer1.getState()))
                .andExpect(jsonPath("$.postalCode").value(customer1.getPostalCode()))
                .andExpect(jsonPath("$.country").value(customer1.getCountry()));

        verify(mockRepo, times(1)).findById(1);
        verifyNoMoreInteractions(mockRepo);
    }

    @Test
    public void shouldGetCustomersByState() throws Exception {
        Customer customer1 = new Customer(1, "Chiamaka", "Udechukwu", "chi@gmail.com",
                "Google", "4155703748", "Fenimore", "Brooklyn",
                "New York City", "New York", "11225", "Nigeria");

        Customer customer2 = new Customer(2, "Chi", "Udee", "chiude@gmail.com",
                "Uber", "4155703748", "Fenimore", "Brooklyn",
                "New York City", "New York", "11225", "Nigeria");

        Customer customer3 = new Customer(2, "Chi", "Udee", "chiude@gmail.com",
                "Uber", "4155703748", "Fenimore", "Brooklyn",
                "New York City", "California", "11225", "Nigeria");
        List<Customer> customers = Arrays.asList(customer1, customer2);

        // Simulate the list of customers returned by the repository for a given state
        when(mockRepo.findByState(customer1.getState())).thenReturn(customers);

        // Perform the GET request to the /customers/state/{state} endpoint
        mockMvc.perform(get("/customers/state/{state}", customer1.getState()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(customers.size()))
                .andExpect(jsonPath("$[0].id").value(customer1.getId()))
                .andExpect(jsonPath("$[0].firstName").value(customer1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(customer1.getLastName()))
                .andExpect(jsonPath("$[1].id").value(customer2.getId()))
                .andExpect(jsonPath("$[1].firstName").value(customer2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(customer2.getLastName()));


        verify(mockRepo, times(1)).findByState(customer1.getState());
        verifyNoMoreInteractions(mockRepo);
    }
}
