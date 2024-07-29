package com.in28minutes.microservices.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping(value = "/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,
                                                  @PathVariable String to) {
        String port = environment.getProperty("local.server.port");
        CurrencyExchange curr = repository.findByFromAndTo(from, to);

        if(curr ==null) {
            throw new RuntimeException("Unable to Find data for " + from + " to " + to);
        }
       // CurrencyExchange curr =  new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50));
        curr.setEnvironment(port);
        return curr;

    }
}
