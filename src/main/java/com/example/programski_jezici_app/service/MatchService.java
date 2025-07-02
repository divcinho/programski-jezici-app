package com.example.programski_jezici_app.service;

import com.example.programski_jezici_app.entity.Match;
import com.example.programski_jezici_app.repo.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;

    public List<Match> getMatches(){
        return repository.findAllByDeletedAtIsNull();
    }

    public List<Match> getFutureMatches(){
        return repository.findAllByStartsAtAfterAndDeletedAtIsNull(LocalDateTime.now());
    }

    public Optional<Match> getMatchById(Integer id){
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public Match createMatch(Match model){
        Match match = new Match();
        match.setHomeTeam(model.getHomeTeam());
        match.setAwayTeam(model.getAwayTeam());
        match.setHomeTeamLogoUrl(model.getHomeTeamLogoUrl());
        match.setAwayTeamLogoUrl(model.getAwayTeamLogoUrl());
        match.setCompetition(model.getCompetition());
        match.setStartsAt(model.getStartsAt());
        match.setStadium(model.getStadium());
        match.setCreatedAt(LocalDateTime.now());
        return repository.save(match);
    }

    public Match updateMatch(Integer id, Match model){
        Match match = getMatchById(id).orElseThrow();
        match.setHomeTeam(model.getHomeTeam());
        match.setAwayTeam(model.getAwayTeam());
        match.setHomeTeamLogoUrl(model.getHomeTeamLogoUrl());
        match.setAwayTeamLogoUrl(model.getAwayTeamLogoUrl());
        match.setCompetition(model.getCompetition());
        match.setStartsAt(model.getStartsAt());
        match.setStadium(model.getStadium());
        match.setUpdatedAt(LocalDateTime.now());
        return repository.save(match);
    }

    public void deleteMatch(Integer id){
        Match match = getMatchById(id).orElseThrow();
        match.setDeletedAt(LocalDateTime.now());
        repository.save(match);
    }
}
