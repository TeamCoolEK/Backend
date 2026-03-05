package org.example.backend.service;

import org.example.backend.model.Seat;
import org.example.backend.model.Showing;
import org.example.backend.repository.*;
import org.springframework.stereotype.Service;

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

    public ReservationService(SeatReservationRepository seatReservationRepository, ReservationRepository reservationRepository, CostumerRepository costumerRepository, ShowingRepository showingRepository, SeatRepositoy seatRepositoy) {
        this.SeatReservationRepository = seatReservationRepository;
        this.ReservationRepository = reservationRepository;
        this.CostumerRepository = costumerRepository;
        this.ShowingRepository = showingRepository;
        this.SeatRepositoy = seatRepositoy;
        this.showingRepository = showingRepository;
    }

    public List<Showing> findAllShowings() {
        return showingRepository.findAll();
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
}
