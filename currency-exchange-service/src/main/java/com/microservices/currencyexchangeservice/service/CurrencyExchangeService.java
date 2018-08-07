package com.microservices.currencyexchangeservice.service;

import com.microservices.currencyexchangeservice.exception.ExchangeValueNotFoundException;
import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyExchangeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ExchangeValueRepository repository;

    @Autowired
    public CurrencyExchangeService(ExchangeValueRepository repository) {
        this.repository = repository;
    }

    public ExchangeValue retrieveExchangeValue(String from, String to) {
        final Optional<ExchangeValue> exchangeValue = repository.findByFromAndTo(from, to);

        if (exchangeValue.isPresent()) {
            logger.info("ExchangeValue: {}", exchangeValue);
            return exchangeValue.get();
        }

        logger.info("Currency exchange {} to {} was not found", from, to);
        throw new ExchangeValueNotFoundException(String.format("Currency exchange %s to %s was not found", from, to));
    }
}