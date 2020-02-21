package com.cryptocurrencypricesapi.services;

import com.cryptocurrencypricesapi.models.CryptoPriceResponse;

public interface CryptocurrencyService {

    CryptoPriceResponse getLatestCryptocurrencies(String start, String limit, String convert);

}
