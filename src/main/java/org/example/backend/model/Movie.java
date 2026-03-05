package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Movie {

    @Id
    private int id;

    private String titel;
    private int ageLimit;
    private Boolean isActive;
    private int duration; // minutter
    private boolean isUnderperforming;


    @OneToMany(mappedBy = "movie")
    @JsonBackReference
    private List<Showing> showings;


    @ManyToOne
    @JoinColumn(name = "categoryID", referencedColumnName = "id")
    private Category category;
}
