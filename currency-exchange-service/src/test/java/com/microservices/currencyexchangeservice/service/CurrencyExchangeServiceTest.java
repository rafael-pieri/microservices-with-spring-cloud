package com.microservices.currencyexchangeservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeServiceTest {

    private static final String EURO = "EUR";
    private static final String DOLAR = "USD";

    @InjectMocks
    private CurrencyExchangeService currencyExchangeService;

    @Mock
    private ExchangeValueRepository exchangeValueRepository;

    @Test
    public void retrieveExchangeValue() {
        final ExchangeValue exchangeValue = new ExchangeValue(1L, EURO, DOLAR, BigDecimal.valueOf(1.2));

        when(this.exchangeValueRepository.findByFromAndTo(any(), any())).thenReturn(exchangeValue);

        ExchangeValue exchangeValueResult = this.currencyExchangeService.retrieveExchangeValue(EURO, DOLAR);

        verify(this.exchangeValueRepository, times(1)).findByFromAndTo(any(), any());

        assertEquals(Long.valueOf(1), exchangeValueResult.getId());
        assertEquals(EURO, exchangeValueResult.getFrom());
        assertEquals(DOLAR, exchangeValueResult.getTo());
        assertEquals(BigDecimal.valueOf(1.2), exchangeValueResult.getConversionMultiple());
    }
}