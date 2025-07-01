package com.example.programski_jezici_app.controller;

import com.example.programski_jezici_app.entity.Customer;
import com.example.programski_jezici_app.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/api/customer")
@CrossOrigin
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository repository;

    @GetMapping
    public List<Customer> getCustomers(){
        return repository.findAllByDeletedAtIsNull();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id){
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody Customer model){
        Customer customer = new Customer();
        customer.setName(model.getName());
        customer.setSurname(model.getSurname());
        customer.setEmailAddress(model.getEmailAddress());
        customer.setPhoneNumber(model.getPhoneNumber());
        customer.setCreatedAt(LocalDateTime.now());
        return repository.save(customer);
    }

    @PutMapping(path = "/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer model){
        Customer customer = repository.findById(id).orElseThrow();
        customer.setName(model.getName());
        customer.setSurname(model.getSurname());
        customer.setEmailAddress(model.getEmailAddress());
        customer.setPhoneNumber(model.getPhoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());
        return repository.save(customer);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Integer id){
        Customer customer = repository.findById(id).orElseThrow();
        customer.setDeletedAt(LocalDateTime.now());
        repository.save(customer);
    }
}
