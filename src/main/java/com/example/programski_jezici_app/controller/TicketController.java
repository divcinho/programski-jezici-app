package com.example.programski_jezici_app.controller;

import com.example.programski_jezici_app.entity.Ticket;
import com.example.programski_jezici_app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
