package org.example.backend.service;

import org.example.backend.model.Category;
import org.example.backend.model.Movie;
import org.example.backend.model.Showing;
import org.example.backend.repository.CategoryRepository;
import org.example.backend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class MovieService {

    public final MovieRepository MovieRepository;
    public final CategoryRepository CategoryRepository;

    private final ReservationService reservationService;

    public MovieService(MovieRepository MovieRepository, CategoryRepository CategoryRepository, ReservationService reservationService) {
        this.MovieRepository = MovieRepository;
        this.CategoryRepository = CategoryRepository;
        this.reservationService = reservationService;
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


    public void updateUnderperformingStatus() {
        List<Movie> movies = MovieRepository.findAll();

        for (Movie movie : movies) {
            List<Showing> lastWeekShowings = movie.getShowings().stream()
                    .filter(showing -> showing.getStartTime().isAfter(LocalDateTime.now().minusWeeks(1)))
                    .toList();

            if (lastWeekShowings.isEmpty()) {
                movie.setUnderperforming(false); //Hvis der ikke er nogen showings, så kan de ikke underperforme
            } else {
                //Her beregner vi gennemsnitligt salg
                double averageSoldRatio = lastWeekShowings.stream().mapToDouble(showing -> {
                    int soldSeats = reservationService.countSoldSeatsForShowing(showing.getId());
                    int capacity = showing.getTheatre().getCapacity();
                    return (double) soldSeats / capacity;
                }).average().orElse(1.0);

                movie.setUnderperforming(averageSoldRatio <= 0.4);
            }

            MovieRepository.save(movie); //Gemmer ændringer
        }
    }
}

