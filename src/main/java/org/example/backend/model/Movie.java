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
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private int ageLimit;
    private Boolean isActive;
    private int duration; // minutter

    private boolean underperforming;

    @Lob
    @Column(columnDefinition = "LONGTEXT") // Gemmer billedet som en lang tekststreng i Base64 format
    private String imageData;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Showing> showings;


    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    private Category category;
}
