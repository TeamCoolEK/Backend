package org.example.backend.controller;

import org.example.backend.model.Seat;
import org.example.backend.model.Showing;
import org.example.backend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")

public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("showings/{showingId}/available-seats")
    public List<Seat> getAvailableSeatsForShowing(@PathVariable int showingId) {
        return reservationService.findAvailableSeatsForShowing(showingId);
    }
    //Henter showings for specifik dato til index
    @GetMapping("/showbydate")
    public ResponseEntity<List<Showing>> getShowingsByDate (@RequestParam String startDate) {
        LocalDateTime localDateTime = LocalDateTime.parse(startDate);
        List<Showing> showingsByDate = reservationService.getShowsByDate(localDateTime);
        return ResponseEntity.ok(showingsByDate);
    }
}
