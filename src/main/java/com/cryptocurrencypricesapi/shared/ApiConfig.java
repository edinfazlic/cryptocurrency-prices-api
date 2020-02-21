package com.cryptocurrencypricesapi.shared;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
public class ApiConfig {

    @Value("${CRYPTO_API_URL}")
    private String cryptoApiUrl;

    @Value("${CRYPTO_API_KEY}")
    private String cryptoApiKey;

}
