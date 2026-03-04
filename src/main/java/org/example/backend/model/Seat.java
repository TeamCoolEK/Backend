package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Seat {

    @Id
    private int Id;

    private int rowNo;
    private int seatNo;
    private int status;


    @OneToMany(mappedBy = "seat")
    @JsonBackReference
    private List<SeatReservation> seatReservations;

    @ManyToOne
    @JoinColumn(name = "theatreID", referencedColumnName = "id")
    private Theatre theatre;

}
