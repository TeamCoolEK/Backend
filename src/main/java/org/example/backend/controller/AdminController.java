package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.model.Reservation;
import org.example.backend.model.Showing;
import org.example.backend.service.AdminService;
import org.example.backend.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController retunerer Json (ResponseBody), hvor Controller retunerer HTML view (som 2 semester / WishList)
//Vi bygger en API, så derfor RestController
@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;
    private final ReservationService reservationService;

    public AdminController(ReservationService reservationService, AdminService adminService) {
        this.adminService = adminService;
        this.reservationService = reservationService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMoviesForAdmin() {
        adminService.updateUnderperformingStatus();
        return ResponseEntity.ok(adminService.getAllMovies());
    }

    @DeleteMapping("/deletemovie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        adminService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/allmovies")
    public List<Movie> getAllMovies() {
        return adminService.getAllMovies();
    }

    @PostMapping("/createmovie")
    public Movie createMovie(@RequestBody Movie movie) {
        return adminService.createMovie(movie);
    }

    @GetMapping("/showallshowings")
    public ResponseEntity<List<Showing>> getAllShowings () {
        List<Showing> showings = adminService.findAllShowings();
        return ResponseEntity.ok(showings);
    }

    @PostMapping("/addshowing")
    public ResponseEntity<Showing> addShowing (@RequestBody Showing showing) {
        boolean overlap = adminService.hasOverlap(showing);

        if (overlap) {
            throw new RuntimeException("Fejl");
        }

        Showing savedShowing = adminService.addShowing(showing);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShowing);
    }
  
    @DeleteMapping("/deleteshowing/{id}")
    public void deleteShowing (@PathVariable int id) {
        adminService.deleteShowing(id);
    }

    @GetMapping("/allreservations")
    public List<Reservation> getAllReservations() {
        return adminService.getAllReservations();
    }

    @DeleteMapping("/deletereservation/{id}")
    public void deleteReservation (@PathVariable int id) {
        adminService.deleteReservation(id);
    }
}
