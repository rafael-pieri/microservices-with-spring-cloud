# Spring MicroServices

## Overview
This repository contains an example of how to implement microservices with Spring Cloud.

### How to deploy the application
Execute the following command to deploy the application:

`docker-compose up`

### Ports

|     Application       |     Port          |
| ------------- | ------------- |
| Currency Exchange Service | 8000 |
| Currency Conversion Service | 8100 |
| Netflix Eureka Naming Server | 8761 |
| Netflix Zuul API Gateway Server | 8765 |
| Zipkin Distributed Tracing Server | 9411 |


### URLs

|     Application       |     URL          |
| ------------- | ------------- |
| Currency Exchange Service | http://localhost:8000/api/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR|
| Currency Converter Service | http://localhost:8100/api/currency-converter/from/USD/to/INR/quantity/10|
| Eureka Server | http://localhost:8761/|
| Zuul - Currency Exchange & Exchange Services | http://localhost:8765/api/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/api/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10|
| Zipkin | http://localhost:9411/zipkin/ |
| Spring Cloud Bus Refresh | http://localhost:8080/bus/refresh |

### Swagger-UI

|     Application       |     URL         |
| ------------- | ------------- |
| Currency Exchange Service | http://localhost:8000/swagger-ui.html |
| Currency Conversion Service | http://localhost:8100/swagger-ui.html |
