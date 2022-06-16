package com.roi.demos.domain.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

@SpringBootTest
public class TestStubTradeHandlerServiceImpl {
    @Autowired
    private TradeHandlerService tradeHandlerService;

    @Test
    public void testGetTradesByTypeForCash(){
        StepVerifier.create(tradeHandlerService.getTradesByType("Cash"))
            .expectNextCount(9)
            .expectComplete()
            .verify();
    }

    @Test
    public void testGetTradesByTypeForSecurity(){
        StepVerifier.create(tradeHandlerService.getTradesByType("Security"))
            .expectNextCount(10)
            .expectComplete()
            .verify();
    }
}
