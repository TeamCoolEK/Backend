package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int capacity;

    @OneToMany(mappedBy = "theatre")
    @JsonIgnore
    private List<Showing> showings;

    @OneToMany(mappedBy = "theatre")
    @JsonBackReference
    private List<Seat> seats;
}
