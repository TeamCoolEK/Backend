package org.example.backend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalTime startTime;
    private LocalTime endTime;
    private int status;

    @OneToMany(mappedBy = "showing")
    @JsonBackReference
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "movieID", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatreID", referencedColumnName = "id")
    private Theatre theatre;
}
