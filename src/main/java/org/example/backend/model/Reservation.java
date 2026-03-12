package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private int price;

    @OneToMany(mappedBy = "reservation")
    private List<SeatReservation> seatReservations;

    @ManyToOne
    @JoinColumn(name = "customerid", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "showingid", referencedColumnName = "id")
    private Showing showing;
}
