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

    private static final String CALLING_CURRENCY_EXCHANGE_SERVICE = "Calling currency exchange service...";
    private static final String CURRENCY_EXCHANGE_SERVICE_RESPONSE = "currency exchange service response: {}";
    private final CurrencyExchangeServiceProxy currencyExchangeServiceProxy;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CurrencyConversionService(CurrencyExchangeServiceProxy currencyExchangeServiceProxy) {
        this.currencyExchangeServiceProxy = currencyExchangeServiceProxy;
    }

    public CurrencyConversionDTO convertCurrency(String from, String to, BigDecimal quantity) {
        logger.info(CALLING_CURRENCY_EXCHANGE_SERVICE);

        final CurrencyConversionDTO response = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

        logger.info(CURRENCY_EXCHANGE_SERVICE_RESPONSE, response);

        return new CurrencyConversionDTO(response.getId(), from, to, response.getConversionMultiple(), quantity,
            quantity.multiply(response.getConversionMultiple()));
    }
}