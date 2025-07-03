package com.example.programski_jezici_app.service;

import com.example.programski_jezici_app.entity.Ticket;
import com.example.programski_jezici_app.repo.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository repository;

    public List<Ticket> getTickets(){
        return repository.findAllByDeletedAtIsNull();
    }
}
