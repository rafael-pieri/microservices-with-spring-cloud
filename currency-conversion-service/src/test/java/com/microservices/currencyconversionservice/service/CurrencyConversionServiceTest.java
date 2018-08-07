package com.microservices.currencyconversionservice.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservices.currencyconversionservice.client.CurrencyExchangeServiceProxy;
import com.microservices.currencyconversionservice.dto.CurrencyConversionDTO;
import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConversionServiceTest {

    private static final String EURO = "EUR";
    private static final String DOLAR = "USD";

    @InjectMocks
    private CurrencyConversionService currencyConversionService;

    @Mock
    private CurrencyExchangeServiceProxy currencyExchangeServiceProxy;

    @Test
    public void convertCurrency() {
        final CurrencyConversionDTO currencyConversionDTO = new CurrencyConversionDTO();
        currencyConversionDTO.setId(1L);
        currencyConversionDTO.setFrom(EURO);
        currencyConversionDTO.setTo(DOLAR);
        currencyConversionDTO.setQuantity(BigDecimal.valueOf(1000));
        currencyConversionDTO.setConversionMultiple(BigDecimal.valueOf(1.2));
        currencyConversionDTO.setTotalCalculatedAmount(BigDecimal.valueOf(1200));

        when(this.currencyExchangeServiceProxy.retrieveExchangeValue(any(), any())).thenReturn(currencyConversionDTO);

        CurrencyConversionDTO currencyConversion = this.currencyConversionService.convertCurrency(EURO, DOLAR,
            BigDecimal.valueOf(1000));

        verify(this.currencyExchangeServiceProxy, times(1)).retrieveExchangeValue(any(), any());

        assertEquals(Long.valueOf(1), currencyConversion.getId());
        assertEquals(EURO, currencyConversion.getFrom());
        assertEquals(DOLAR, currencyConversion.getTo());
        assertEquals(BigDecimal.valueOf(1000), currencyConversion.getQuantity());
        assertEquals(BigDecimal.valueOf(1.2), currencyConversion.getConversionMultiple());
        assertEquals(BigDecimal.valueOf(1200.0), currencyConversion.getTotalCalculatedAmount());
    }
}