package org.example.backend.controller;

import org.example.backend.service.MovieService;
import org.example.backend.service.ReservationService;
import org.example.backend.service.TheatreService;
import org.springframework.stereotype.Controller;

@Controller
public class AdminController {

    private final TheatreService TheatreService;
    private final ReservationService ReservationService;
    private final MovieService MovieService;

    public AdminController(TheatreService theatreService, ReservationService reservationService, MovieService movieService) {
        TheatreService = theatreService;
        ReservationService = reservationService;
        MovieService = movieService;
    }
}
