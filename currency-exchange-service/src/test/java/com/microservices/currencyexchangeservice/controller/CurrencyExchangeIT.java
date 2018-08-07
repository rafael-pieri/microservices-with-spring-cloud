package com.microservices.currencyexchangeservice.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.microservices.currencyexchangeservice.model.ExchangeValue;
import com.microservices.currencyexchangeservice.service.CurrencyExchangeService;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurrencyExchangeIT {

    private static final String EURO = "EUR";
    private static final String DOLAR = "USD";

    @MockBean
    private CurrencyExchangeService currencyExchangeService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.context)
            .build();
    }

    @Test
    public void retrieveExchangeValue() throws Exception {
        final ExchangeValue exchangeValue = new ExchangeValue(1L, EURO, DOLAR, BigDecimal.valueOf(1.2));

        when(this.currencyExchangeService.retrieveExchangeValue(any(), any())).thenReturn(exchangeValue);

        this.mockMvc
            .perform(get(String.format("/api/currency-exchange/from/%s/to/%s", EURO, DOLAR))
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.from").value(EURO))
            .andExpect(jsonPath("$.to").value(DOLAR))
            .andExpect(jsonPath("$.conversionMultiple").value(1.2))
            .andExpect(status().isOk())
            .andReturn();
    }
}