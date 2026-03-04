package org.example.backend.service;

import org.example.backend.repository.SeatRepositoy;
import org.example.backend.repository.TheatreRepository;
import org.springframework.stereotype.Service;

@Service
public class TheatreService {

    private final SeatRepositoy SeatRepositoy;
    private final TheatreRepository TheatreRepository;

    public TheatreService(SeatRepositoy seatRepositoy, TheatreRepository theatreRepository) {
        SeatRepositoy = seatRepositoy;
        TheatreRepository = theatreRepository;
    }
}
