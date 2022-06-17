package com.roi.demos.domain.controller;

import com.roi.demos.domain.entity.Trade;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.scheduler.Schedulers;

@SpringBootTest
class TradeControllerTest {

    @Autowired
    private ApplicationContext ctx;
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client = WebTestClient.bindToApplicationContext(ctx).build();
    }

    @AfterEach
    void tearDown() {
        client = null;
    }

    @Test
    void testGetTradeTxtStream() {
        var expectedType = new ParameterizedTypeReference<ServerSentEvent<Trade>>() {
        };

        client.get().uri("/trade/tradeTxtStream")
            .accept(MediaType.TEXT_EVENT_STREAM)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .returnResult(expectedType)
            .getResponseBody()
            .parallel(4)
            .runOn(Schedulers.parallel())
            .subscribe(System.out::println);
    }
}