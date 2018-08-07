package com.microservices.currencyexchangeservice.repository;

import com.microservices.currencyexchangeservice.model.ExchangeValue;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {

    Optional<ExchangeValue> findByFromAndTo(String from, String to);
}