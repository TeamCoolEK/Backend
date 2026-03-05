package org.example.backend.service;

import org.example.backend.model.Seat;
import org.example.backend.model.Showing;
import org.example.backend.model.Theatre;
import org.example.backend.repository.SeatRepositoy;
import org.example.backend.repository.SeatReservationRepository;
import org.example.backend.repository.ShowingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ShowingRepository showingRepository;

    @Mock
    private SeatRepositoy seatRepositoy;

    @Mock
    private SeatReservationRepository seatReservationRepository;

    @InjectMocks
    private ReservationService service;

    @Test
    void findAvailableSeatsForShowing_returnsOnlyNotReservedAndStatus0() {
        int showingId = 5;
        int theatreId = 1;

        Theatre theatre = new Theatre();
        theatre.setId(theatreId);

        Showing showing = new Showing();
        showing.setId(showingId);
        showing.setTheatre(theatre);

        Seat seat1 = new Seat(); seat1.setId(1); seat1.setStatus(0);
        Seat seat2 = new Seat(); seat2.setId(2); seat2.setStatus(0);
        Seat seat3 = new Seat(); seat3.setId(3); seat3.setStatus(1); // broken
        Seat seat4 = new Seat(); seat4.setId(4); seat4.setStatus(0);

        when(showingRepository.findById(showingId)).thenReturn(Optional.of(showing));
        when(seatRepositoy.findByTheatre_Id(theatreId)).thenReturn(List.of(seat1, seat2, seat3, seat4));
        when(seatReservationRepository.findReservedSeatIdsByShowingId(showingId)).thenReturn(List.of(2, 4));

        // Act
        List<Seat> result = service.findAvailableSeatsForShowing(showingId);

        // Assert
        // Ledige og status==0: seat1 (id=1) -> ja
        // seat2 (id=2) reserveret -> nej
        // seat3 (id=3) status=1 -> nej
        // seat4 (id=4) reserveret -> nej
        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getId());
    }
}