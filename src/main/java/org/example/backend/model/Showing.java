package org.example.backend.model;
import lombok.Data;

import java.time.LocalTime;

@Data
public class Showing {
    private int ID;
    private LocalTime StartTime;
    private LocalTime EndTime;
    private int Status;
}
