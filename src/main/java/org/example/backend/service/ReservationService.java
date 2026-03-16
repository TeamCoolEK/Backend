package org.example.backend.service;

import org.example.backend.model.*;
import org.example.backend.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    private final SeatReservationRepository SeatReservationRepository;
    private final ReservationRepository ReservationRepository;
    private final CostumerRepository CostumerRepository;
    private final ShowingRepository ShowingRepository;
    private final SeatRepositoy SeatRepositoy;
    private final ShowingRepository showingRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(SeatReservationRepository seatReservationRepository, ReservationRepository reservationRepository, CostumerRepository costumerRepository, ShowingRepository showingRepository, SeatRepositoy seatRepositoy) {
        this.SeatReservationRepository = seatReservationRepository;
        this.ReservationRepository = reservationRepository;
        this.CostumerRepository = costumerRepository;
        this.ShowingRepository = showingRepository;
        this.SeatRepositoy = seatRepositoy;
        this.showingRepository = showingRepository;
        this.reservationRepository = reservationRepository;
    }

    public int countSoldSeatsForShowing(int showingId) {
        return SeatReservationRepository.findReservedSeatIdsByShowingId(showingId).size();
    }

    public List<Seat> findSeatsByTheatre(int theatreId){
        return SeatRepositoy.findByTheatre_Id(theatreId);
    }

    public List<Seat> findAvailableSeatsForShowing(int showingId) {

        Showing showing = showingRepository.findById(showingId).orElse(null);
        int theatreId = showing.getTheatre().getId();
        List<Seat> allSeats = SeatRepositoy.findByTheatre_Id(theatreId);
        List<Integer> reservedSeatIds =
                SeatReservationRepository.findReservedSeatIdsByShowingId(showingId);

        List<Seat> availableSeats = new ArrayList<>();

        for (Seat seat : allSeats) {
            boolean isReserved = false;
            for (Integer reservedId : reservedSeatIds) {
                if (seat.getId() == reservedId) {
                    isReserved = true;
                    break;
                }
            }

            if (!isReserved && seat.getStatus() == 0) {
                availableSeats.add(seat);
            }
        }
        return availableSeats;
    }
    //Henter liste af showings pr dato og sotere dem efter tid
    public List<Showing> getShowsByDate (LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);

        List<Showing> showings =
                showingRepository.findByStartTimeBetween(startOfDay, endOfDay);

        showings.sort((a, b) -> a.getStartTime().compareTo(b.getStartTime()));

        return showings;

    }

    public String createReservation(int showingId, String phoneNr, List<Integer> seatIds){

        Showing showing = showingRepository.findById(showingId).orElse(null);

        if (showing == null){
            return "Showing not found";
        }

        Customer customer = CostumerRepository.findByPhoneNr(phoneNr);

        if (customer == null){
            customer = new Customer();
            customer.setPhoneNr(phoneNr);
            customer = CostumerRepository.save(customer);
        }

        List<Integer> reservedSeatIds = SeatReservationRepository.findReservedSeatIdsByShowingId(showingId);

        for (Integer seatId : seatIds){
            for (Integer reservedId : reservedSeatIds) {
                if (seatId.intValue() == reservedId.intValue()){
                    return "One or more seats are already reserved";
                }
            }
        }

        Reservation reservation = new Reservation();
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        reservation.setPrice(seatIds.size() * 100);
        reservation.setCustomer(customer);
        reservation.setShowing(showing);

        reservation = reservationRepository.save(reservation);

        for (Integer seatId : seatIds){
            Seat seat = SeatRepositoy.findById(seatId).orElse(null);

            if (seat != null){
                SeatReservation seatReservation = new SeatReservation();
                seatReservation.setSeat(seat);
                seatReservation.setReservation(reservation);
                SeatReservationRepository.save(seatReservation);
            }
        }
        return "Thanks for you reservation.";
    }

    public Showing findShowingById(int showingId) {
        return showingRepository.findById(showingId).orElse(null);
    }

}
