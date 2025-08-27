package org.ongpatinhas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptchaResponseDTO {

    private boolean success;
    @JsonProperty("challenge_ts")
    private String challengeTs;
    private String hostname;
    @JsonProperty("error-codes")
    private String[] errorCodes;

}
