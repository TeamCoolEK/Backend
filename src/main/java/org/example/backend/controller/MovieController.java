package org.example.backend.controller;

import org.example.backend.model.Category;
import org.example.backend.model.Movie;
import org.example.backend.repository.CategoryRepository;
import org.example.backend.repository.MovieRepository;
import org.example.backend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class MovieController {

    private final MovieService MovieService;
    private final MovieRepository MovieRepository;
    private final CategoryRepository CategoryRepository;

    public MovieController(MovieService movieService, MovieRepository movieRepository, CategoryRepository categoryRepository) {
        MovieService = movieService;
        MovieRepository = movieRepository;
        CategoryRepository = categoryRepository;

    }

    @GetMapping("/allmovies")
    public List<Movie> getAllMovies() {
        return MovieService.getAllMovies();
    }

    @PostMapping("createmovie")
    public Movie createMovie(@RequestBody Movie movie) {
        return MovieService.createMovie(movie);
    }

    @DeleteMapping("/deletemovie/{id}")
    public void deleteMovie(@PathVariable int id) {
        MovieService.deleteMovie(id);
    }
}



