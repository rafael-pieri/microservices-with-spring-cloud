package com.microservices.currencyconversionservice.service;

import com.microservices.currencyconversionservice.client.CurrencyExchangeServiceProxy;
import com.microservices.currencyconversionservice.dto.CurrencyConversionDTO;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @Autowired
    public CurrencyConversionService(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    public CurrencyConversionDTO convertCurrency(String from, String to, BigDecimal quantity) {
        logger.info("Calling currency exchange service...");

        final CurrencyConversionDTO response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        logger.info("Currency exchange service response: {}", response);

        return new CurrencyConversionDTO(response.getId(), from, to, response.getConversionMultiple(), quantity,
            quantity.multiply(response.getConversionMultiple()));
    }
}