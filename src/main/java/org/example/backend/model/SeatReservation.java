package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Reservation reservation;
}   /*JsonIgnore for at få antallet af sæder med i visning af reservationer i admin siden,
    og for at undgå uendelig løkke.
*/
