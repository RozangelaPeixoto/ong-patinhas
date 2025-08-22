package org.ongpatinhas.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CaptchaService {

    private static final String VERIFY_URL =
            "https://www.google.com/recaptcha/api/siteverify";

    @Value("${spring.captcha.secret-key}")
    private String secretKey;

    public boolean validateCaptcha(String captchaResponse) {
        RestTemplate restTemplate = new RestTemplate();

        String url = VERIFY_URL + "?secret=" + secretKey + "&response=" + captchaResponse;

        ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);

        Map body = response.getBody();

        return body != null && (Boolean) body.get("success");
    }

}
