package com.microservices.currencyexchangeservice.service;

import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private static final String EXCHANGE_VALUE = "ExchangeValue: {}";
    private final ExchangeValueRepository repository;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public CurrencyExchangeService(ExchangeValueRepository repository) {
        this.repository = repository;
    }

    public ExchangeValue retrieveExchangeValue(String from, String to) {
        final ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        logger.info(EXCHANGE_VALUE, exchangeValue);

        return exchangeValue;
    }
}