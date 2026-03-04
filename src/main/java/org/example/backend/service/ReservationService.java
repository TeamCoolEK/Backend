package org.example.backend.service;

import org.example.backend.repository.*;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final SeatReservationRepository SeatReservationRepository;
    private final ReservationRepository ReservationRepository;
    private final CostumerRepository CostumerRepository;
    private final ShowingRepository ShowingRepository;
    private final SeatRepositoy SeatRepositoy;

    public ReservationService(SeatReservationRepository seatReservationRepository, ReservationRepository reservationRepository, CostumerRepository costumerRepository, ShowingRepository showingRepository, SeatRepositoy seatRepositoy) {
        SeatReservationRepository = seatReservationRepository;
        ReservationRepository = reservationRepository;
        CostumerRepository = costumerRepository;
        ShowingRepository = showingRepository;
        SeatRepositoy = seatRepositoy;
    }
}
