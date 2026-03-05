package org.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SeatReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatReservationID;

    @ManyToOne
    @JoinColumn(name = "seatID", referencedColumnName = "id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "reservationdID", referencedColumnName = "id")
    private Reservation reservation;
}