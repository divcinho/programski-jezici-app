package com.example.programski_jezici_app.controller;

import com.example.programski_jezici_app.entity.Match;
import com.example.programski_jezici_app.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/api/match")
public class MatchController {

    private final MatchService service;

    @GetMapping
    public List<Match> getMatches(){
        return service.getMatches();
    }

    @GetMapping(path = "/future")
    public  List<Match> getFuture(){
        return service.getFutureMatches();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Match> getById(@PathVariable Integer id){
        return ResponseEntity.of(service.getMatchById(id));
    }

    @PostMapping
    public Match create(@RequestBody Match model){
        return service.createMatch(model);
    }

    @PutMapping(path = "/{id}")
    public Match update(@PathVariable Integer id, @RequestBody Match model){
        return service.updateMatch(id, model);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id){
        service.deleteMatch(id);
    }
}
