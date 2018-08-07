package com.microservices.currencyconversionservice.controller;

import com.microservices.currencyconversionservice.dto.CurrencyConversionDTO;
import com.microservices.currencyconversionservice.service.CurrencyConversionService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionDTO convertCurrency(@PathVariable String from, @PathVariable String to,
                                                 @PathVariable BigDecimal quantity) {
        return currencyConversionService.convertCurrency(from, to, quantity);
    }
}