package org.example.backend.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Showing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime startTime;
    private LocalTime endTime; // = start + movieDuration
    private int status;

    @OneToMany(mappedBy = "showing")
   @JsonIgnore
    private List<Reservation> reservations;

    @ManyToOne
    @JoinColumn(name = "movieid", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theatreid", referencedColumnName = "id")
    private Theatre theatre;
}
