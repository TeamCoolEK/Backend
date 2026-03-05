package org.example.backend.service;

import org.example.backend.model.Showing;
import org.example.backend.repository.ShowingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    private ShowingRepository showingRepository;

    @InjectMocks
    private ReservationService service;

    private Showing showingSample;

    @BeforeEach
    void SetUp () {
        MockitoAnnotations.openMocks(this);
    }
}
