package org.example.backend.service;

import org.example.backend.model.Movie;
import org.example.backend.repository.CategoryRepository;
import org.example.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;


@Service
public class MovieService {

    public final MovieRepository MovieRepository;
    public final CategoryRepository CategoryRepository;

    public MovieService(MovieRepository MovieRepository, CategoryRepository CategoryRepository){
        this.MovieRepository = MovieRepository;
        this.CategoryRepository = CategoryRepository;
    }


//    public Movie createMovie(Movie movie) {
//        return MovieRepository.save(movie);
//    }

}
