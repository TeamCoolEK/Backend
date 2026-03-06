package org.example.backend.controller;

import org.example.backend.model.Movie;
import org.example.backend.repository.MovieRepository;
import org.example.backend.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MovieController {

    private final MovieService MovieService;
    private final MovieRepository MovieRepository;

    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        MovieService = movieService;
        MovieRepository = movieRepository;
    }

    @GetMapping("/allmovies")
    public List<Movie> getAllMovies() {
        return MovieRepository.findAll();
    }

    @PostMapping("/createmovie")
    public Movie createMovie(@RequestBody Movie movie) {
        return MovieRepository.save(movie);
    }
    }


