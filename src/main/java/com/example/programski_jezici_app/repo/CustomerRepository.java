package com.example.programski_jezici_app.repo;

import com.example.programski_jezici_app.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllByDeletedAtIsNull();

    Optional<Customer> findByIdAndDeletedAtIsNull(Integer id);

    Boolean existsByIdAndDeletedAtIsNull(Integer id);
}
