package org.ongpatinhas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DonationDTO(
        @NotBlank(message = "O nome é obrigatório")
        String name,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotNull(message = "O valor é obrigatório")
        @DecimalMin(value = "1.00", message = "O valor mínimo é R$ 1,00")
        BigDecimal value
) {

}
