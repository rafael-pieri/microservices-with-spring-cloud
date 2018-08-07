package com.microservices.zuulapigateway.config;

import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlwaysSamplerConfig {

    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }
}