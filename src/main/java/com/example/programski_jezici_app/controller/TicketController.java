package com.example.programski_jezici_app.controller;

import com.example.programski_jezici_app.entity.Ticket;
import com.example.programski_jezici_app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/ticket")
public class TicketController {

    private final TicketService service;

    @GetMapping
    public List<Ticket> getTickets(){
        return service.getTickets();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id){
        return ResponseEntity.of(service.getTicketById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createTicket(@RequestBody Ticket ticket){
        service.createTicket(ticket);
    }

    @PutMapping(path = "/{id}/pay")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void payTicket(@PathVariable Integer id){
        service.payTicket(id);
    }
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTicket(@PathVariable Integer id ,@RequestBody Ticket ticket){
        service.updateTicket(id, ticket);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTicket(@PathVariable Integer id){
        service.deleteTicket(id);
    }
}
