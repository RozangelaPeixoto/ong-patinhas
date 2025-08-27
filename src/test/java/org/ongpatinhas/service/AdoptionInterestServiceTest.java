// src/test/java/org/ongpatinhas/service/AdoptionInterestServiceTest.java
package org.ongpatinhas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.ongpatinhas.model.AdoptionInterest;
import org.ongpatinhas.repository.AdoptionInterestRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdoptionInterestServiceTest {

    @Mock
    private AdoptionInterestRepository adoptionInterestRepository;

    @InjectMocks
    private AdoptionInterestService adoptionInterestService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave_Success() {
        AdoptionInterest interest = new AdoptionInterest();
        when(adoptionInterestRepository.save(any(AdoptionInterest.class))).thenReturn(interest);

        AdoptionInterest result = adoptionInterestService.saveAdoptionInterest(interest);

        assertNotNull(result);
        verify(adoptionInterestRepository, times(1)).save(interest);
    }

}