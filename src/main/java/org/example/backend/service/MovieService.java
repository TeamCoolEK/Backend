package org.example.backend.service;

import org.example.backend.model.Category;
import org.example.backend.model.Movie;
import org.example.backend.repository.CategoryRepository;
import org.example.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;


@Service
public class MovieService {

    public final MovieRepository MovieRepository;
    public final CategoryRepository CategoryRepository;

    public MovieService(MovieRepository MovieRepository, CategoryRepository CategoryRepository){
        this.MovieRepository = MovieRepository;
        this.CategoryRepository = CategoryRepository;
    }

    public List<Movie> getAllMovies() {
        return MovieRepository.findAll();
    }

    public Movie createMovie(Movie movie) {
        Category category = CategoryRepository.findById(movie.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Kategori ikke fundet"));
        movie.setCategory(category);
        return MovieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        MovieRepository.deleteById(id);
    }

}
