package com.cryptocurrencypricesapi.services.impl;

import com.cryptocurrencypricesapi.models.CryptoPriceResponse;
import com.cryptocurrencypricesapi.services.CryptocurrencyService;
import com.cryptocurrencypricesapi.shared.ApiConfig;
import com.cryptocurrencypricesapi.shared.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
public class CryptocurrencyServiceImpl implements CryptocurrencyService {

    private final ApiConfig apiConfig;

    private final WebClient client;


    @Autowired
    public CryptocurrencyServiceImpl(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        client = WebClient
                .builder()
                .defaultHeader(Constant.API_HEADER, this.apiConfig.getCryptoApiKey())
                .baseUrl(this.apiConfig.getCryptoApiUrl())
                .build();
    }

    @Override
    public CryptoPriceResponse getLatestCryptocurrencies(String start, String limit, String convert) {
        WebClient.ResponseSpec response = client
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/cryptocurrency/listings/latest")
                        .queryParam("start", start)
                        .queryParam("limit", limit)
                        .queryParam("convert", convert)
                        .build())
                .retrieve();

        CryptoPriceResponse result = response
                .bodyToMono(String.class)
                .map(s -> new CryptoPriceResponse(true, s))
                .onErrorResume(throwable -> {
                    if (isLimitError(throwable)) {
                        return Mono.just(new CryptoPriceResponse(false, Constant.ERROR_CODE_LIMIT));
                    }
                    return Mono.just(new CryptoPriceResponse(false, Constant.ERROR_CODE_OTHER));
                })
                .block();
        return result;
    }

    private boolean isLimitError(Throwable throwable) {
        return ((WebClientResponseException.BadRequest) throwable)
                .getResponseBodyAsString()
                .contains(Constant.API_LIMIT_ERROR);
    }
}
