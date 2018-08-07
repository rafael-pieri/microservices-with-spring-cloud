package com.microservices.currencyconversionservice.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.microservices.currencyconversionservice.dto.CurrencyConversionDTO;
import com.microservices.currencyconversionservice.service.CurrencyConversionService;
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
public class CurrencyConversionIT {

    private static final String EURO = "EUR";
    private static final String DOLAR = "USD";

    @MockBean
    private CurrencyConversionService currencyConversionService;

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
    public void convertCurrency() throws Exception {
        final CurrencyConversionDTO currencyConversionDTO = new CurrencyConversionDTO();
        currencyConversionDTO.setId(1L);
        currencyConversionDTO.setFrom(EURO);
        currencyConversionDTO.setTo(DOLAR);
        currencyConversionDTO.setQuantity(BigDecimal.valueOf(1000));
        currencyConversionDTO.setConversionMultiple(BigDecimal.valueOf(1.2));
        currencyConversionDTO.setTotalCalculatedAmount(BigDecimal.valueOf(1200));

        when(this.currencyConversionService.convertCurrency(any(), any(), any())).thenReturn(currencyConversionDTO);

        this.mockMvc
            .perform(get(String.format("/api/currency-converter/from/%s/to/%s/quantity/%s", EURO, DOLAR, 1000))
                .accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.from").value(EURO))
            .andExpect(jsonPath("$.to").value(DOLAR))
            .andExpect(jsonPath("$.quantity").value(1000))
            .andExpect(jsonPath("$.conversionMultiple").value(1.2))
            .andExpect(jsonPath("$.totalCalculatedAmount").value(1200))
            .andExpect(status().isOk())
            .andReturn();
    }
}