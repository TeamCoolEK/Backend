package org.example.backend.model;

import lombok.Data;

@Data
public class Movie {
    private int ID;
    private String Titel;
    private int AgeLimit;
    private Boolean IsActive;
    private int Duration;
    private int Category;
    private boolean IsUnderperforming;
}
