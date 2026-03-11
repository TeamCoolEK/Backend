package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Seat {

    @Id
    private int id;

    private int rowNo;
    private int seatNo;
    private int status;


    @OneToMany(mappedBy = "seat")
    @JsonBackReference
    private List<SeatReservation> seatReservations;

    @ManyToOne
    @JoinColumn(name = "theatreid", referencedColumnName = "id")
    private Theatre theatre;

}
