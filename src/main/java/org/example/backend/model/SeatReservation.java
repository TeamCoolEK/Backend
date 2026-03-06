package org.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SeatReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatReservationID;

    @ManyToOne
    @JoinColumn(name = "seatid", referencedColumnName = "id")
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "reservationdid", referencedColumnName = "id")
    private Reservation reservation;
}