package com.cryptocurrencypricesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CryptoPriceResponse {
    private boolean valid;
    private Object data;
}
