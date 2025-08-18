package org.ongpatinhas.repository;

import org.ongpatinhas.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepository extends JpaRepository<Donation, String> {
}
