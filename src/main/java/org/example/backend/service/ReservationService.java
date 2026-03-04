package org.example.backend.service;

import org.example.backend.model.Showing;
import org.example.backend.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final SeatReservationRepository SeatReservationRepository;
    private final ReservationRepository ReservationRepository;
    private final CostumerRepository CostumerRepository;
    private final ShowingRepository ShowingRepository;
    private final SeatRepositoy SeatRepositoy;
    private final ShowingRepository showingRepository;

    public ReservationService(SeatReservationRepository seatReservationRepository, ReservationRepository reservationRepository, CostumerRepository costumerRepository, ShowingRepository showingRepository, SeatRepositoy seatRepositoy, ShowingRepository showingRepository) {
        SeatReservationRepository = seatReservationRepository;
        ReservationRepository = reservationRepository;
        CostumerRepository = costumerRepository;
        ShowingRepository = showingRepository;
        SeatRepositoy = seatRepositoy;
        this.showingRepository = showingRepository;
    }

    public List<Showing> findAllShowings () {
        return showingRepository.findAll();
    }
}
