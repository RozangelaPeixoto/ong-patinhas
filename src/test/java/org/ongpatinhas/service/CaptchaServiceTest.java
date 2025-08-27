package org.ongpatinhas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ongpatinhas.dto.CaptchaResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class CaptchaServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CaptchaService captchaService;

    private static final String SECRET_KEY = "test-secret-key";
    private static final String CAPTCHA_RESPONSE = "test-captcha-response";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(captchaService, "secretKey", SECRET_KEY);

        // Mock da criação do RestTemplate
        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(anyString(), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(createSuccessResponse());
        })) {
            // O mock será aplicado quando o RestTemplate for criado
        }
    }

    @Test
    void testValidateCaptcha_Success() {
        // Arrange
        CaptchaResponseDTO successResponse = new CaptchaResponseDTO();
        successResponse.setSuccess(true);
        ResponseEntity<CaptchaResponseDTO> responseEntity = ResponseEntity.ok(successResponse);

        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(anyString(), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(responseEntity);
        })) {
            // Act
            boolean result = captchaService.validateCaptcha(CAPTCHA_RESPONSE);

            // Assert
            assertTrue(result);
        }
    }

    @Test
    void testValidateCaptcha_Failure() {
        // Arrange
        CaptchaResponseDTO failureResponse = new CaptchaResponseDTO();
        failureResponse.setSuccess(false);
        ResponseEntity<CaptchaResponseDTO> responseEntity = ResponseEntity.ok(failureResponse);

        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(anyString(), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(responseEntity);
        })) {
            // Act
            boolean result = captchaService.validateCaptcha(CAPTCHA_RESPONSE);

            // Assert
            assertFalse(result);
        }
    }

    @Test
    void testValidateCaptcha_NullResponse() {
        // Arrange
        ResponseEntity<CaptchaResponseDTO> responseEntity = ResponseEntity.ok(null);

        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(anyString(), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(responseEntity);
        })) {
            // Act
            boolean result = captchaService.validateCaptcha(CAPTCHA_RESPONSE);

            // Assert
            assertFalse(result);
        }
    }

    @Test
    void testValidateCaptcha_WithErrorCodes() {
        // Arrange
        CaptchaResponseDTO errorResponse = new CaptchaResponseDTO();
        errorResponse.setSuccess(false);
        errorResponse.setErrorCodes(new String[]{"invalid-input-response", "timeout-or-duplicate"});
        ResponseEntity<CaptchaResponseDTO> responseEntity = ResponseEntity.ok(errorResponse);

        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(anyString(), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(responseEntity);
        })) {
            // Act
            boolean result = captchaService.validateCaptcha(CAPTCHA_RESPONSE);

            // Assert
            assertFalse(result);
        }
    }

    @Test
    void testValidateCaptcha_CorrectUrlConstruction() {
        // Arrange
        String expectedUrl = "https://www.google.com/recaptcha/api/siteverify?secret=" + SECRET_KEY + "&response=" + CAPTCHA_RESPONSE;
        CaptchaResponseDTO successResponse = new CaptchaResponseDTO();
        successResponse.setSuccess(true);
        ResponseEntity<CaptchaResponseDTO> responseEntity = ResponseEntity.ok(successResponse);

        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(eq(expectedUrl), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(responseEntity);
        })) {
            // Act
            boolean result = captchaService.validateCaptcha(CAPTCHA_RESPONSE);

            // Assert
            assertTrue(result);
        }
    }

    @Test
    void testValidateCaptcha_EmptyCaptchaResponse() {
        // Arrange
        CaptchaResponseDTO failureResponse = new CaptchaResponseDTO();
        failureResponse.setSuccess(false);
        ResponseEntity<CaptchaResponseDTO> responseEntity = ResponseEntity.ok(failureResponse);

        try (var mockedConstructor = mockConstruction(RestTemplate.class, (mock, context) -> {
            when(mock.postForEntity(anyString(), any(), eq(CaptchaResponseDTO.class)))
                    .thenReturn(responseEntity);
        })) {
            // Act
            boolean result = captchaService.validateCaptcha("");

            // Assert
            assertFalse(result);
        }
    }

    private ResponseEntity<CaptchaResponseDTO> createSuccessResponse() {
        CaptchaResponseDTO response = new CaptchaResponseDTO();
        response.setSuccess(true);
        response.setChallengeTs("2024-08-24T19:17:39Z");
        response.setHostname("localhost");
        return ResponseEntity.ok(response);
    }
}
