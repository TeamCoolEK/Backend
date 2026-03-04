package org.example.backend.controller;

import org.example.backend.service.ReservationService;
import org.springframework.stereotype.Controller;

@Controller
public class ReservationController {

    private final ReservationService ReservationService;

    public ReservationController(ReservationService reservationService) {
        ReservationService = reservationService;
    }
}
