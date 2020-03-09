package com.cryptocurrencypricesapi.controllers;

import com.cryptocurrencypricesapi.models.CryptoPriceResponse;
import com.cryptocurrencypricesapi.services.CryptocurrencyService;
import com.cryptocurrencypricesapi.services.impl.CryptocurrencyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*") // todo, remove wildcard star
public class CryptocurrencyPricesController {
    private CryptocurrencyService cryptocurrencyService;

    @Autowired
    public CryptocurrencyPricesController(CryptocurrencyServiceImpl cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @RequestMapping(value = "/latest-cryptocurrencies", method = RequestMethod.GET)
    public Object getLatestCryptocurrencies(@RequestParam String start,
                              @RequestParam String limit,
                              @RequestParam String convert) {
        CryptoPriceResponse latestCryptocurrencies = cryptocurrencyService.getLatestCryptocurrencies(start, limit, convert);
        if (latestCryptocurrencies.isValid()) {
            return ResponseEntity.ok(latestCryptocurrencies.getData());
        } else {
            return ResponseEntity.badRequest().body(latestCryptocurrencies.getData());
        }
    }
}
