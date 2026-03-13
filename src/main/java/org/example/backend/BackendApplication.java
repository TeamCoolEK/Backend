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

//    @Bean
//    public CommandLineRunner importData(CategoryRepository categoryRepository,
//                                        CostumerRepository costumerRepository,
//                                        MovieRepository movieRepository,
//                                        ReservationRepository reservationRepository,
//                                        SeatRepositoy seatRepositoy,
//                                        SeatReservationRepository seatReservationRepository,
//                                        ShowingRepository showingRepository,
//                                        TheatreRepository theatreRepository) {
//        return (args) -> {
//
//            final List<Theatre> theatres = new ArrayList<>();
//
//            Theatre theatre1 = new Theatre();
//            theatre1.setName("Theatre 1");
//            theatre1.setCapacity(240);
//
//            Theatre theatre2 = new Theatre();
//            theatre2.setName("Theatre 2");
//            theatre2.setCapacity(400);
//
//            theatres.add(theatre1);
//            theatres.add(theatre2);
//
//            theatreRepository.saveAll(theatres);
//
//
//            // Sætter vores hardkodet seats direkte i koden!
//            final List<Seat> seats = new ArrayList<>();
//            // Theatre 1
//            for (int row = 1; row <= 20; row++) {
//                for (int seatNo = 1; seatNo <= 12; seatNo++) {
//
//                    int id = (row - 1) * 12 + seatNo;
//
//                    Seat seat = new Seat();
//                    seat.setId(id);
//                    seat.setRowNo(row);
//                    seat.setSeatNo(seatNo);
//                    seat.setStatus(0);
//                    seat.setTheatre(theatre1);
//
//                    seats.add(seat);
//                }
//            }
//
//
//            // Theatre 2
//            for (int row = 1; row <= 25; row++) {
//                for (int seatNo = 1; seatNo <= 16; seatNo++) {
//
//                    int id = 240 + (row - 1) * 16 + seatNo;
//
//                    Seat seat = new Seat();
//                    seat.setId(id);
//                    seat.setRowNo(row);
//                    seat.setSeatNo(seatNo);
//                    seat.setStatus(0);
//                    seat.setTheatre(theatre2);
//
//                    seats.add(seat);
//                }
//            }
//            seatRepositoy.saveAll(seats);
//
//            // fetch an individual customer by ID
//            final Optional<Seat> seat = seatRepositoy.findById(1);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(seat.toString());
//            log.info("");
//
//            // =======================
//            // CATEGORY
//            // =======================
//            if (categoryRepository.count() == 0) {
//                Category scienceFiction = new Category();
//                scienceFiction.setName("Science Fiction");
//                categoryRepository.save(scienceFiction);
//
//                Category action = new Category();
//                action.setName("Action");
//                categoryRepository.save(action);
//
//                Category drama = new Category();
//                drama.setName("Drama");
//                categoryRepository.save(drama);
//
//                Category komedie = new Category();
//                komedie.setName("Komedie");
//                categoryRepository.save(komedie);
//
//                Category thriller = new Category();
//                thriller.setName("Thriller");
//                categoryRepository.save(thriller);
//
//                Category animation = new Category();
//                animation.setName("Animation");
//                categoryRepository.save(animation);
//
//                Category horror = new Category();
//                horror.setName("Horror");
//                categoryRepository.save(horror);
//            }
//
//            // =======================
//            // MOVIE
//            // =======================
//            Movie movie = new Movie();
//            movie.setTitle("Interstellar");
//            movie.setAgeLimit(11);
//            movie.setIsActive(true);
//            movie.setDuration(169);
//            movie.setUnderperforming(false);
//            movie.setCategory(categoryRepository.findById(1).get());
//
//            movieRepository.save(movie);
//
//
//            // =======================
//            // SHOWING
//            // =======================
//
//            Showing showing = new Showing();
//
//            showing.setStartTime(LocalDateTime.of(2026,3,6,18,0));
//            showing.setEndTime(LocalDateTime.of(2026, 3, 6,  20,49));
//
//            showing.setStatus(0);
//            showing.setMovie(movie);
//
//            // henter theatre 1 fra databasen
//            Theatre theatre = theatreRepository.findById(1).get();
//            showing.setTheatre(theatre);
//
//            showingRepository.save(showing);
//
//
//            // =======================
//            // CUSTOMER
//            // =======================
//
//            Customer customer = new Customer();
//            customer.setName("Sebastian");
//            customer.setAge((byte) 25);
//            customer.setPhoneNr("42450177");
//
//            costumerRepository.save(customer);
//
//
//            // =======================
//            // RESERVATION
//            // =======================
//
//            Reservation reservation = new Reservation();
//
//            reservation.setCreatedAt(LocalDateTime.of(2026,3,6,17,30));
//            reservation.setExpiresAt(LocalDateTime.of(2026,3,6,17,50));
//
//            reservation.setPrice(120);
//
//            reservation.setCustomer(customer);
//            reservation.setShowing(showing);
//
//            reservationRepository.save(reservation);
//
//
//            // =======================
//            // SEAT RESERVATION
//            // =======================
//
//            // henter seat 1 fra databasen
//            Seat seat1 = seatRepositoy.findById(1).get();
//
//            SeatReservation seatReservation = new SeatReservation();
//
//            seatReservation.setSeat(seat1);
//            seatReservation.setReservation(reservation);
//
//            seatReservationRepository.save(seatReservation);
//        };
//    }
}


