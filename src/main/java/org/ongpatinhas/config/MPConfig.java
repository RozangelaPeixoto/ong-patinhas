package org.ongpatinhas.config;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MPConfig {

    @Value("${spring.mercadopago.access-token}")
    private String accessToken;

    @PostConstruct
    public void init() throws MPException{
        MercadoPagoConfig.setAccessToken(accessToken);
    }

}
