package org.example.backend.controller;

import org.example.backend.service.MovieService;
import org.springframework.stereotype.Controller;

@Controller
public class MovieController {

    private final MovieService MovieService;

    public MovieController(MovieService movieService) {
        MovieService = movieService;
    }
}
