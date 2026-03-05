package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private int price;

    @OneToMany(mappedBy = "reservation")
    @JsonBackReference
    private List<SeatReservation> seatReservations;

    @ManyToOne
    @JoinColumn(name = "customerId", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "showingId", referencedColumnName = "id")
    private Showing showing;
}
