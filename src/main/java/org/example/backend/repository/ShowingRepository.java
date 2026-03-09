package org.example.backend.repository;

import org.example.backend.model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {

    List<Showing> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
}
