package com.microservices.currencyconversionservice.client;

import com.microservices.currencyconversionservice.dto.CurrencyConversionDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "zuul-api-gateway")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/currency-exchange-service/api/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionDTO retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}