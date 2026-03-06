package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Theatre {

    @Id
    private int id;

    private String name;
    private int capacity;

    @OneToMany(mappedBy = "theatre")
    @JsonBackReference
    private List<Showing> showings;

    @OneToMany(mappedBy = "theatre")
    @JsonBackReference
    private List<Seat> seats;
}
