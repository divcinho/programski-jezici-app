package com.example.programski_jezici_app.repo;

import com.example.programski_jezici_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByDeletedAtIsNull();
}
