package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldAddCustomers() {

        //Arrange...
        // Need to create a Label and an Artist first
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");


        customer = customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getId());
        assertThat(customer1.equals(customer));
    }


    @Test
    public void shouldUpdateCustomer() {
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer = customerRepository.save(customer);

        customer.setFirstName("Obianuju");
        customer.setLastName("Mbanaefo");

        customerRepository.save(customer);

        //Assert...
        Customer customer1 = customerRepository.findById(customer.getId());
        assertThat(customer1.equals(customer));
    }

    @Test
    public void shouldDeleteCustomer() {
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer = customerRepository.save(customer);
        Customer customer1 = customerRepository.findById(customer.getId());
        assertThat(customer1.equals(customer));
        customerRepository.save(customer);

        customerRepository.deleteById(customer.getId());
        customer1 = customerRepository.findById(customer.getId());
        assertFalse(customer1.equals(customer));
    }

    @Test
    public void shouldGetCustomers() {
        Customer customer = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer = customerRepository.save(customer);

        Customer customer2 = new Customer(2, "Chi", "Udee",
                "chiude@gmail.com", "Uber", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer2 = customerRepository.save(customer2);

        List<Customer> customerList = customerRepository.findAll();

        assertEquals(customerList.size(), 2);
    }

    @Test
    public void shouldGetAlbumById() {

        Customer customer1 = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer1 = customerRepository.save(customer1);

        Customer customer2 = new Customer(2, "Chi", "Udee",
                "chiude@gmail.com", "Uber", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer2 = customerRepository.save(customer2);

        Customer foundCustomer = customerRepository.findById(customer1.getId());
        assertThat(foundCustomer.equals(customer1));
    }

    @Test
    public void shouldGetAlbumByState() {

        Customer customer1 = new Customer(1, "Chiamaka", "Udechukwu",
                "chi@gmail.com", "Google", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer1 = customerRepository.save(customer1);

        Customer customer2 = new Customer(2, "Chi", "Udee",
                "chiude@gmail.com", "Uber", "4155703748", "Fenimore",
                "Brooklyn", "New York City", "New York", "11225",
                "Nigeria");

        customer2 = customerRepository.save(customer2);

        List<Customer> foundCustomer = customerRepository.findByState(customer1.getState());
        assertThat(foundCustomer.equals(customer1));
    }
}