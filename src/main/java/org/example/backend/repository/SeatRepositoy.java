package org.example.backend.repository;

import org.example.backend.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepositoy extends JpaRepository<Seat, Integer> {

    List<Seat> findByTheatre_Id (int theatreId);
}
