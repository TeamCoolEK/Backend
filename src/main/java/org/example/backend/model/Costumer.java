package org.example.backend.model;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Costumer {
    private int ID;
    private LocalTime CreatedAt;
    private LocalTime ExpiresAt;
    private int Price;
}
