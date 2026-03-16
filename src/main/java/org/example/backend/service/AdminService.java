package org.example.backend.service;

import org.example.backend.model.Category;
import org.example.backend.model.Movie;
import org.example.backend.model.Reservation;
import org.example.backend.model.Showing;
import org.example.backend.repository.CategoryRepository;
import org.example.backend.repository.MovieRepository;
import org.example.backend.repository.ReservationRepository;
import org.example.backend.repository.ShowingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@Service
public class AdminService {

    public final MovieRepository MovieRepository;
    public final CategoryRepository CategoryRepository;
    private final ReservationService reservationService;
    private final ShowingRepository showingRepository;
    private final ReservationRepository reservationRepository;

    public AdminService(MovieRepository MovieRepository, CategoryRepository CategoryRepository, ReservationService reservationService, ShowingRepository showingRepository, ReservationRepository reservationRepository) {
        this.MovieRepository = MovieRepository;
        this.CategoryRepository = CategoryRepository;
        this.reservationService = reservationService;
        this.showingRepository = showingRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Movie> getAllMovies() {
        return MovieRepository.findAll();
    }

    public List<Showing> findAllShowings() {
        return showingRepository.findAll();
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

    public Showing addShowing (Showing showing) { //Tilføjer showing til databasen
        List<Showing> showings = showingRepository.findAll();
        //Exception, hvis showing har samme id
        for (Showing s : showings) {
            if (s.getId() == showing.getId()) {
                throw new RuntimeException("Showing already exist!");
            }
        }
        //Hvis showings endtime (beregnet fra start tid og films længde) overskrider 23:59, kan showing ikke oprettes
        if (showing.getEndTime().toLocalTime().isAfter(LocalTime.of(23,59))) {
            throw new IllegalArgumentException("Showing cannot end after 23:59");
        }
        return showingRepository.save(showing);
    }

    public void deleteShowing (int id) {
        showingRepository.deleteById(id);
    }

    public boolean hasOverlap(Showing showing) { //tjekker om en showing overlapper en anden
        List<Showing> showings = showingRepository.findAll();

        //Hvis showing starttid og slutid ikke er indenfor en showing i listen, retuneres true, ellers false
        for (Showing s : showings) {
            //Hvis showing sal er lig med showing sal i listen tjekkes der for overlap.
            if (s.getTheatre().getId() == showing.getTheatre().getId()) {

                if (showing.getStartTime().isBefore(s.getEndTime()) && showing.getEndTime().isAfter(s.getStartTime())) {
                    return true;
                }

            }
        }
        return false;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservation (int id) {
        reservationRepository.deleteById(id);
    }
}

