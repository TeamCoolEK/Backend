package org.example.backend;


import org.example.backend.model.*;
import org.example.backend.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class BackendApplication {

    private static final Logger log = LoggerFactory.getLogger(BackendApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    //Omdanner jpg til base64 string til dummy data
    public String imageToBase64(String fileName) {
        try {
            ClassPathResource resource = new ClassPathResource("images/" + fileName);
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = inputStream.readAllBytes();

            String base64 = Base64.getEncoder().encodeToString(bytes);

            String mimeType = "image/jpeg";
            if (fileName.endsWith(".png")) {
                mimeType = "image/png";
            } else if (fileName.endsWith(".webp")) {
                mimeType = "image/webp";
            }

            return "data:" + mimeType + ";base64," + base64;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + fileName, e);
        }
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

            // =======================
            // MOVIES
            // =======================

            List<Movie> movies = new ArrayList<>();

            Movie interstellar = new Movie();
            interstellar.setTitle("Interstellar");
            interstellar.setAgeLimit(11);
            interstellar.setIsActive(true);
            interstellar.setDuration(169);
            interstellar.setUnderperforming(false);
            interstellar.setCategory(categoryRepository.findById(1).get());
            interstellar.setImageData(imageToBase64("interstellar.jpg"));

            Movie dune = new Movie();
            dune.setTitle("Dune: Part Two");
            dune.setAgeLimit(11);
            dune.setIsActive(true);
            dune.setDuration(166);
            dune.setUnderperforming(false);
            dune.setCategory(categoryRepository.findById(1).get());
            dune.setImageData(imageToBase64("dune.jpg"));

            Movie johnWick = new Movie();
            johnWick.setTitle("John Wick 4");
            johnWick.setAgeLimit(15);
            johnWick.setIsActive(true);
            johnWick.setDuration(169);
            johnWick.setUnderperforming(false);
            johnWick.setCategory(categoryRepository.findById(2).get());
            johnWick.setImageData(imageToBase64("johnWick.jpg"));

            Movie oppenheimer = new Movie();
            oppenheimer.setTitle("Oppenheimer");
            oppenheimer.setAgeLimit(15);
            oppenheimer.setIsActive(true);
            oppenheimer.setDuration(180);
            oppenheimer.setUnderperforming(false);
            oppenheimer.setCategory(categoryRepository.findById(3).get());
            oppenheimer.setImageData(imageToBase64("oppenheimer.jpg"));

            Movie mario = new Movie();
            mario.setTitle("Super Mario Bros");
            mario.setAgeLimit(7);
            mario.setIsActive(true);
            mario.setDuration(92);
            mario.setUnderperforming(false);
            mario.setCategory(categoryRepository.findById(6).get());
            mario.setImageData(imageToBase64("mario.jpg"));

            movies.add(interstellar);
            movies.add(dune);
            movies.add(johnWick);
            movies.add(oppenheimer);
            movies.add(mario);

            movieRepository.saveAll(movies);


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

               // =======================
              // SHOWINGS
             // =======================

            List<Showing> showings = new ArrayList<>();

            Theatre theatre1Db = theatreRepository.findById(1).get();
            Theatre theatre2Db = theatreRepository.findById(2).get();

            LocalDate startDate = LocalDate.of(2026, 3, 13);

            for (int day = 0; day < 14; day++) {

                LocalDate date = startDate.plusDays(day);

                // 15:00 showing
                Showing showing1 = new Showing();
                showing1.setStartTime(LocalDateTime.of(date, LocalTime.of(15,0)));
                showing1.setEndTime(LocalDateTime.of(date, LocalTime.of(17,30)));
                showing1.setStatus(0);
                showing1.setMovie(movies.get(day % movies.size()));
                showing1.setTheatre(theatre1Db);
                showings.add(showing1);

                // 18:00 showing
                Showing showing2 = new Showing();
                showing2.setStartTime(LocalDateTime.of(date, LocalTime.of(18,0)));
                showing2.setEndTime(LocalDateTime.of(date, LocalTime.of(20,45)));
                showing2.setStatus(0);
                showing2.setMovie(movies.get((day+1) % movies.size()));
                showing2.setTheatre(theatre2Db);
                showings.add(showing2);

                // 21:00 showing
                Showing showing3 = new Showing();
                showing3.setStartTime(LocalDateTime.of(date, LocalTime.of(21,0)));
                showing3.setEndTime(LocalDateTime.of(date, LocalTime.of(23,50)));
                showing3.setStatus(0);
                showing3.setMovie(movies.get((day+2) % movies.size()));
                showing3.setTheatre(theatre1Db);
                showings.add(showing3);
            }

            showingRepository.saveAll(showings);

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
//            reservation.setShowing(showings.get(1));
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

            Random random = new Random();

            // hent data fra DB
            List<Seat> seatsDb = seatRepositoy.findAll();


            // =======================
            // CUSTOMERS (50)
            // =======================

            List<Customer> customers = new ArrayList<>();

            for (int i = 1; i <= 50; i++) {

                Customer c = new Customer();
                c.setName("Customer " + i);
                c.setAge((byte) (18 + random.nextInt(50)));
                c.setPhoneNr("40" + (100000 + random.nextInt(899999)));

                customers.add(c);
            }

            costumerRepository.saveAll(customers);


            // =======================
            // RESERVATIONS (300)
            // =======================

            List<Reservation> reservations = new ArrayList<>();

            for (int i = 0; i < 300; i++) {

                Customer randomCustomer = customers.get(random.nextInt(customers.size()));
                Showing randomShowing = showings.get(random.nextInt(showings.size()));

                Reservation r = new Reservation();

                LocalDateTime created = randomShowing.getStartTime().minusHours(2);

                r.setCreatedAt(created);
                r.setExpiresAt(created.plusMinutes(20));
                r.setPrice(120);

                r.setCustomer(randomCustomer);
                r.setShowing(randomShowing);

                reservations.add(r);
            }

            reservationRepository.saveAll(reservations);


            // =======================
            // SEAT RESERVATIONS (1000)
            // =======================

            List<SeatReservation> seatReservations = new ArrayList<>();

            for (int i = 0; i < 1000; i++) {

                Seat randomSeat = seatsDb.get(random.nextInt(seatsDb.size()));
                Reservation randomReservation = reservations.get(random.nextInt(reservations.size()));

                SeatReservation sr = new SeatReservation();

                sr.setSeat(randomSeat);
                sr.setReservation(randomReservation);

                seatReservations.add(sr);
            }

            seatReservationRepository.saveAll(seatReservations);
        };
    }
}


