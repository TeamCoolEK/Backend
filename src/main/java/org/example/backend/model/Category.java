package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Category {

    @Id
    private int id;

    private String name;

    @OneToMany(mappedBy = "category")
    @JsonBackReference
    private List <Movie> movies;

}
