package com.example.programski_jezici_app.service;

import com.example.programski_jezici_app.entity.Customer;
import com.example.programski_jezici_app.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public List<Customer> getCustomers(){
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Customer> getCustomerById(Integer id){
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Customer createCustomer(Customer model){
        Customer customer = new Customer();
        customer.setName(model.getName());
        customer.setSurname(model.getSurname());
        customer.setEmailAddress(model.getEmailAddress());
        customer.setPhoneNumber(model.getPhoneNumber());
        customer.setCreatedAt(LocalDateTime.now());
        return repository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer model){
        Customer customer = getCustomerById(id).orElseThrow();
        customer.setName(model.getName());
        customer.setSurname(model.getSurname());
        customer.setEmailAddress(model.getEmailAddress());
        customer.setPhoneNumber(model.getPhoneNumber());
        customer.setUpdatedAt(LocalDateTime.now());
        return repository.save(customer);
    }

    public void deleteCustomer(Integer id){
        Customer customer = getCustomerById(id).orElseThrow();
        customer.setDeletedAt(LocalDateTime.now());
        repository.save(customer);
    }
}
