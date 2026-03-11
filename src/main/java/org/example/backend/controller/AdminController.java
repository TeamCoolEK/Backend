package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.model.Showing;
import org.example.backend.service.MovieService;
import org.example.backend.service.ReservationService;
import org.example.backend.service.TheatreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController retunerer Json (ResponseBody), hvor Controller retunerer HTML view (som 2 semester / WishList)
//Vi bygger en API, så derfor RestController
@RestController
@CrossOrigin(origins = "*")
public class AdminController {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ReservationService reservationService;

    public AdminController(TheatreService theatreService, ReservationService reservationService, MovieService movieService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
        this.reservationService = reservationService;
    }

    @GetMapping("/showallshowings")
    public ResponseEntity<List<Showing>> getAllShowings () {
        List<Showing> showings = reservationService.findAllShowings();
        return ResponseEntity.ok(showings);
    }

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMoviesForAdmin() {
        movieService.updateUnderperformingStatus();
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping("/addshowing")
    public ResponseEntity<Showing> addShowing (@RequestBody Showing showing) {
        boolean overlap = reservationService.hasOverlap(showing);

        if (overlap) {
            throw new RuntimeException("Fejl");
        }

        Showing savedShowing = reservationService.addShowing(showing);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedShowing);
    }

    @DeleteMapping("/movie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
  
    @DeleteMapping("/deleteshowing/{id}")
    public void deleteShowing (@PathVariable int id) {
        reservationService.deleteShowing(id);
    }
}
