package com.example.programski_jezici_app.repo;

import com.example.programski_jezici_app.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match, Integer> {

    List<Match> findAllByDeletedAtIsNull();

    Optional<Match> findByIdAndDeletedAtIsNull(Integer id);

    List<Match> findAllByStartsAtAfterAndDeletedAtIsNull(LocalDateTime time);
}
