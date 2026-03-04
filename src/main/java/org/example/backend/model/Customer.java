package org.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private byte age;
    private String phoneNr;
}
