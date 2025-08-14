package org.ongpatinhas.dto;

import java.math.BigDecimal;

public record DonationDTO(String name, String email, BigDecimal value) {
}
