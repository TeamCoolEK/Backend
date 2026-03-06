package org.example.backend.controller;

import org.example.backend.model.Seat;
import org.example.backend.service.ReservationService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/showings/{showingId}/available-seats")
    public List<Seat> getAvailableSeatsForShowing(@PathVariable int showingId) {
        return reservationService.findAvailableSeatsForShowing(showingId);
    }
}
