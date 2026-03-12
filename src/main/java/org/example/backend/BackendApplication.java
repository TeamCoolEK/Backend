package org.example.backend;

import org.example.backend.model.*;
import org.example.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BackendApplication {

    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner importData(CategoryRepository categoryRepository,
                                        CostumerRepository costumerRepository,
                                        MovieRepository movieRepository,
                                        ReservationRepository reservationRepository,
                                        SeatRepositoy seatRepositoy,
                                        SeatReservationRepository seatReservationRepository,
                                        ShowingRepository showingRepository,
                                        TheatreRepository theatreRepository) {
        return (args) -> {

            final List<Theatre> theatres = new ArrayList<>();

            Theatre theatre1 = new Theatre();
            theatre1.setName("Theatre 1");
            theatre1.setCapacity(240);

            Theatre theatre2 = new Theatre();
            theatre2.setName("Theatre 2");
            theatre2.setCapacity(400);

            theatres.add(theatre1);
            theatres.add(theatre2);

            theatreRepository.saveAll(theatres);


            // Sætter vores hardkodet seats direkte i koden!
            final List<Seat> seats = new ArrayList<>();
            // Theatre 1
            for (int row = 1; row <= 20; row++) {
                for (int seatNo = 1; seatNo <= 12; seatNo++) {

                    int id = (row - 1) * 12 + seatNo;

                    Seat seat = new Seat();
                    seat.setId(id);
                    seat.setRowNo(row);
                    seat.setSeatNo(seatNo);
                    seat.setStatus(0);
                    seat.setTheatre(theatre1);

                    seats.add(seat);
                }
            }


            // Theatre 2
            for (int row = 1; row <= 25; row++) {
                for (int seatNo = 1; seatNo <= 16; seatNo++) {

                    int id = 240 + (row - 1) * 16 + seatNo;

                    Seat seat = new Seat();
                    seat.setId(id);
                    seat.setRowNo(row);
                    seat.setSeatNo(seatNo);
                    seat.setStatus(0);
                    seat.setTheatre(theatre2);

                    seats.add(seat);
                }
            }
            seatRepositoy.saveAll(seats);

            // fetch an individual customer by ID
            final Optional<Seat> seat = seatRepositoy.findById(1);
            log.info("Customer found with findById(1L):");
            log.info("--------------------------------");
            log.info(seat.toString());
            log.info("");

            // =======================
            // CATEGORY
            // =======================
            if (categoryRepository.count() == 0) {
                Category scienceFiction = new Category();
                scienceFiction.setName("Science Fiction");
                categoryRepository.save(scienceFiction);

                Category action = new Category();
                action.setName("Action");
                categoryRepository.save(action);

                Category drama = new Category();
                drama.setName("Drama");
                categoryRepository.save(drama);

                Category komedie = new Category();
                komedie.setName("Komedie");
                categoryRepository.save(komedie);

                Category thriller = new Category();
                thriller.setName("Thriller");
                categoryRepository.save(thriller);

                Category animation = new Category();
                animation.setName("Animation");
                categoryRepository.save(animation);

                Category horror = new Category();
                horror.setName("Horror");
                categoryRepository.save(horror);
            }

            // =======================
            // MOVIE
            // =======================
            Movie movie = new Movie();
            movie.setTitle("Interstellar");
            movie.setAgeLimit(11);
            movie.setIsActive(true);
            movie.setDuration(169);
            movie.setUnderperforming(false);
            movie.setCategory(categoryRepository.findById(1).get());

            String interstellarImage = "data:image/jpeg;base64,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";

            movie.setImageData(interstellarImage);
            movieRepository.save(movie);


            // =======================
            // SHOWING
            // =======================

            Showing showing = new Showing();

            showing.setStartTime(LocalDateTime.of(2026,3,12,18,0));
            showing.setEndTime(LocalDateTime.of(2026, 3, 12,  20,49));

            showing.setStatus(0);
            showing.setMovie(movie);

            // henter theatre 1 fra databasen
            Theatre theatre = theatreRepository.findById(1).get();
            showing.setTheatre(theatre);

            showingRepository.save(showing);


            // =======================
            // CUSTOMER
            // =======================

            Customer customer = new Customer();
            customer.setName("Sebastian");
            customer.setAge((byte) 25);
            customer.setPhoneNr("42450177");

            costumerRepository.save(customer);


            // =======================
            // RESERVATION
            // =======================

            Reservation reservation = new Reservation();

            reservation.setCreatedAt(LocalDateTime.of(2026,3,6,17,30));
            reservation.setExpiresAt(LocalDateTime.of(2026,3,6,17,50));

            reservation.setPrice(120);

            reservation.setCustomer(customer);
            reservation.setShowing(showing);

            reservationRepository.save(reservation);


            // =======================
            // SEAT RESERVATION
            // =======================

            // henter seat 1 fra databasen
            Seat seat1 = seatRepositoy.findById(1).get();

            SeatReservation seatReservation = new SeatReservation();

            seatReservation.setSeat(seat1);
            seatReservation.setReservation(reservation);

            seatReservationRepository.save(seatReservation);
        };
    }
}


