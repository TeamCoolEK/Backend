package org.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Theatre {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int capasity;
}
