package org.example.backend.repository;

import org.example.backend.model.SeatReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatReservationRepository extends JpaRepository<SeatReservation, Integer> {

    @Query("select sr.seat.id from SeatReservation sr where sr.reservation.showing.id = :showingId")
    List<Integer> findReservedSeatIdsByShowingId(int showingId);
}
