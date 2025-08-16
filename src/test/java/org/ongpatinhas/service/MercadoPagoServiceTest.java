package org.ongpatinhas.service;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.ongpatinhas.dto.DonationDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MercadoPagoServiceTest {

    @Mock
    private DonationService donationService;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private MercadoPagoService mercadoPagoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        String successUrl = "https://portfolio-azure-nine-95.vercel.app/success";
        String failureUrl = "https://portfolio-azure-nine-95.vercel.app/failure";
        String pendingUrl = "https://portfolio-azure-nine-95.vercel.app/pending";
    }

    @Test
    void testCreateDonationPreference_Success() throws MPException, MPApiException {
        PreferenceClient clientMock = mock(PreferenceClient.class);
        Preference preferenceMock = mock(Preference.class);
        when(preferenceMock.getInitPoint()).thenReturn("http://init-point");
        try (MockedConstruction<PreferenceClient> ignored = mockConstruction(PreferenceClient.class,
                (mock, context) -> when(mock.create(any(), any())).thenReturn(preferenceMock))) {
            DonationDTO dto = new DonationDTO(null, null, BigDecimal.TEN);
            String result = mercadoPagoService.createDonationPreference(dto);
            assertEquals("http://init-point", result);
            verify(donationService, times(1)).createDonation(any(), anyString());
        }
    }

    @Test
    void testCreateDonationPreference_ApiException() throws MPException, MPApiException {
        PreferenceClient clientMock = mock(PreferenceClient.class);
        MPApiException apiException = mock(MPApiException.class);
        when(apiException.getApiResponse()).thenReturn(new com.mercadopago.net.MPResponse(400, new HashMap<>(), "error"));
        try (MockedConstruction<PreferenceClient> ignored = mockConstruction(PreferenceClient.class,
                (mock, context) -> when(mock.create(any(), any())).thenThrow(apiException))) {
            DonationDTO dto = new DonationDTO(null, null, BigDecimal.TEN);
            String result = mercadoPagoService.createDonationPreference(dto);
            assertNotNull(result);
        }
    }

    @Test
    void testCreateDonationPreference_MPException() throws MPException, MPApiException {
        PreferenceClient clientMock = mock(PreferenceClient.class);
        try (MockedConstruction<PreferenceClient> ignored = mockConstruction(PreferenceClient.class,
                (mock, context) -> when(mock.create(any(), any())).thenThrow(new MPException("error")))) {
            DonationDTO dto = new DonationDTO(null, null, BigDecimal.TEN);
            String result = mercadoPagoService.createDonationPreference(dto);
            assertEquals("error", result);
        }
    }

    @Test
    void testProcessPayment_Success() {
        Long paymentId = 123L;
        Map<String, Object> pagamento = new HashMap<>();
        pagamento.put("external_reference", "ref");
        pagamento.put("payment_type_id", "credit_card");
        pagamento.put("status", "approved");

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(pagamento, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), any(ParameterizedTypeReference.class)))
                .thenReturn(responseEntity);

        mercadoPagoService.processPayment(paymentId);

        verify(donationService, times(1))
                .updateDonation("ref", "credit_card", paymentId, "approved");
    }

}
