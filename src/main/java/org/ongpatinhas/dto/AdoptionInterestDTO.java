package org.ongpatinhas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@JsonIgnoreProperties(ignoreUnknown = true)
public record AdoptionInterestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String fullName,

        @AssertTrue(message = "Você precisa ter mais de 18 anos")
        Boolean isAdult,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(
                regexp = "\\d{11}",
                message = "Telefone inválido, precisa ter 11 dígitos"
        )
        String phone,

        @Email(message = "E-mail inválido")
        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        Boolean hadPetsBefore,
        Boolean currentlyHasPets,

        @NotBlank(message = "Por favor, insira um motivo para querer adotar")
        String adoptionReason,

        String dogId) {

    public AdoptionInterestDTO() {
        this("",false , "", "", false, false, "", "");

    }
}
