package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    private int id;

    private String name;
    private byte age;
    private String phoneNr;

    @OneToMany(mappedBy = "customer")
    @JsonBackReference
    private List<Reservation> reservations;
}
