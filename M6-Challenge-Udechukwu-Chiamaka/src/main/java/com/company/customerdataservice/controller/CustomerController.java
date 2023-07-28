package com.company.customerdataservice.controller;

import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerRepository repo;

    @GetMapping("/customers")
    List<Customer> getCustomers() {
        return repo.findAll();
    }

    @GetMapping("/customers/id/{Id}")
    public Customer getCustomersById(@PathVariable int id) {
        return repo.findById(id);
    }

    @GetMapping("/customers/state/{state}")
    public List<Customer> getCustomersByState(@PathVariable String state) {
        return repo.findByState(state);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Customer updateCustomer(@RequestBody Customer customer) {
        return repo.save(customer);
    }

    @PostMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        repo.deleteById(id);
    }
}
