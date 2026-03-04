package org.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Seat {

    @Id
    @GeneratedValue
    private int Id;

    private int rowNo;
    private int seatNo;
    private int status;
}
