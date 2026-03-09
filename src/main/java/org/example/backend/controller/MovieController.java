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
        return MovieRepository.findAll();
    }

//    @PostMapping("/createmovie")
//    public Movie createMovie(@RequestBody Movie movie) {
//        return MovieRepository.save(movie);
//    }
//}

    @PostMapping("/createmovie")
    public Movie createMovie(@RequestBody Movie movie) {
        Category category = CategoryRepository.findById(movie.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category ikke fundet"));
        movie.setCategory(category);
        return MovieRepository.save(movie);
    }
}



