package org.ongpatinhas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.ongpatinhas.dto.DonationDTO;
import org.ongpatinhas.model.Donation;
import org.ongpatinhas.repository.DonationRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DonationServiceTest {

    @Mock
    private DonationRepository donationRepository;

    @InjectMocks
    private DonationService donationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDonation_Success() {
        DonationDTO dto = new DonationDTO(null, null, BigDecimal.TEN);
        Donation donation = new Donation();
        when(donationRepository.save(any(Donation.class))).thenReturn(donation);

        Donation result = donationService.createDonation(dto, "ref123");

        assertNotNull(result);
        verify(donationRepository, times(1)).save(any(Donation.class));
    }

    @Test
    void testUpdateDonation_Success() {
        String referencia = "ref123";
        Donation donation = new Donation();
        when(donationRepository.findById(referencia)).thenReturn(Optional.of(donation));

        boolean updated = donationService.updateDonation(referencia, "credit_card", 123L, "approved");

        assertTrue(updated);
        verify(donationRepository, times(1)).save(donation);
    }

    @Test
    void testUpdateDonation_NotFound() {
        String referencia = "ref123";
        when(donationRepository.findById(referencia)).thenReturn(Optional.empty());

        boolean updated = donationService.updateDonation(referencia, "credit_card", 123L, "approved");

        assertFalse(updated);
        verify(donationRepository, never()).save(any());
    }

    @Test
    void testFindDonationById_Success() {
        String referencia = "ref123";
        Donation donation = new Donation();
        when(donationRepository.findById(referencia)).thenReturn(Optional.of(donation));

        Donation result = donationService.findDonationById(referencia);

        assertNotNull(result);
        assertEquals(donation, result);
    }

    @Test
    void testFindDonationById_NotFound() {
        String referencia = "ref123";
        when(donationRepository.findById(referencia)).thenReturn(Optional.empty());

        Donation result = donationService.findDonationById(referencia);

        assertNull(result);
    }
}
