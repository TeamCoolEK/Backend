package org.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue
    private int id;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private int price;
}
