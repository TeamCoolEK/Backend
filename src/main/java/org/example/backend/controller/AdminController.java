package org.example.backend.controller;

import org.example.backend.model.Showing;
import org.example.backend.service.MovieService;
import org.example.backend.service.ReservationService;
import org.example.backend.service.TheatreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final TheatreService theatreService;
    private final MovieService movieService;
    private final ReservationService reservationService;

    public AdminController(TheatreService theatreService, ReservationService reservationService, MovieService movieService) {
        this.theatreService = theatreService;
        this.movieService = movieService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Showing> getAllShowings () {
        return reservationService.findAllShowings();
    }
}
