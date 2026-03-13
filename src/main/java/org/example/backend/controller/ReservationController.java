package org.example.backend.controller;

import org.example.backend.model.Seat;
import org.example.backend.model.Showing;
import org.example.backend.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    @GetMapping("/showByDate")
    public ResponseEntity<List<Showing>> getShowingsByDate (@RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Showing> showingsByDate = reservationService.getShowsByDate(localDate);
        return ResponseEntity.ok(showingsByDate);
    }

    @PostMapping("/reservations")
    public String createReservation(@RequestParam int showingId, @RequestParam String phoneNr, @RequestParam List<Integer> seatIds){
        return reservationService.createReservation(showingId, phoneNr, seatIds);
    }

    @GetMapping("/showings/{id}")
    public Showing getShowingById(@PathVariable int id) {
        return reservationService.findShowingById(id);
    }
}
