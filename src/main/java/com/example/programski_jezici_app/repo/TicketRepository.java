package com.example.programski_jezici_app.repo;

import com.example.programski_jezici_app.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findAllByDeletedAtIsNull();
}
