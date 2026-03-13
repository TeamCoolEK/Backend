package org.example.backend.repository;

import org.example.backend.model.Showing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ShowingRepositoryTest {

    @Autowired
    private ShowingRepository showingRepository;

    @Test
    void findAll_shouldReturnAllShowings() {
        //Arrange
        Showing s1 = new Showing();
        Showing s2 = new Showing();

        showingRepository.save(s1);
        showingRepository.save(s2);

        //Act
        List<Showing> result = showingRepository.findAll();

        //Assert
        assertEquals(44 , result.size());
    }
}
