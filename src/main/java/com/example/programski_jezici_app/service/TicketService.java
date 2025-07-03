package com.example.programski_jezici_app.service;

import com.example.programski_jezici_app.entity.Customer;
import com.example.programski_jezici_app.entity.Match;
import com.example.programski_jezici_app.entity.Ticket;
import com.example.programski_jezici_app.repo.CustomerRepository;
import com.example.programski_jezici_app.repo.MatchRepository;
import com.example.programski_jezici_app.repo.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final CustomerRepository customerRepository;
    private final MatchRepository matchRepository;

    public List<Ticket> getTickets(){
        return ticketRepository.findAllByDeletedAtIsNull();
    }

    public Optional<Ticket> getTicketById(Integer id){
        return ticketRepository.findByIdAndDeletedAtIsNull(id);
    }

    public void createTicket(Ticket model){
        Ticket ticket = new Ticket();
        ticket.setUuid(UUID.randomUUID().toString());
        ticket.setStand(model.getStand());
        ticket.setSector(model.getSector());
        ticket.setSeat(model.getSeat());
        ticket.setPrice(model.getPrice());

        if(!customerRepository.existsByIdAndDeletedAtIsNull(model.getCustomer().getId()))
            throw new RuntimeException("CUSTOMER_NOT_FOUND");

        Customer customer = new Customer();
        customer.setId(model.getCustomer().getId());
        ticket.setCustomer(customer);

        if(!matchRepository.existsByIdAndDeletedAtIsNull(model.getMatch().getId()))
            throw new RuntimeException("MATCH_NOT_FOUND");

        Match match = new Match();
        match.setId(model.getMatch().getId());
        ticket.setMatch(match);

        ticket.setCreatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public void updateTicket(Integer id, Ticket model){
        Ticket ticket = this.getTicketById(id).orElseThrow();
        ticket.setStand(model.getStand());
        ticket.setSector(model.getSector());
        ticket.setSeat(model.getSeat());
        ticket.setPrice(model.getPrice());

        Customer customer = new Customer();
        customer.setId(model.getCustomer().getId());
        ticket.setCustomer(customer);

        Match match = new Match();
        match.setId(model.getMatch().getId());
        ticket.setMatch(match);

        ticket.setUpdatedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public void payTicket(Integer id){
        Ticket ticket = this.getTicketById(id).orElseThrow();

        if(ticket.getPaidAt() != null)
            throw new RuntimeException("TICKET_ALREADY_PAID");

        ticket.setPaidAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }

    public void deleteTicket(Integer id){
        Ticket ticket = this.getTicketById(id).orElseThrow();
        ticket.setDeletedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
    }
}
