package org.example.backend.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Showing {

    @Id
    @GeneratedValue
    private int id;

    private LocalTime startTime;
    private LocalTime endTime;
    private int status;
}
