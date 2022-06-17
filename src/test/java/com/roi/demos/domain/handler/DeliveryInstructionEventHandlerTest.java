package com.roi.demos.domain.handler;

import com.roi.demos.domain.entity.DeliveryInstruction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;

@Slf4j
@SpringBootTest
class DeliveryInstructionEventHandlerTest {
    @Autowired
    private ApplicationContext ctx;
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        //client = WebTestClient.bindToApplicationContext(ctx).build();
        client = WebTestClient.bindToRouterFunction(ctx.getBean("diRouter", RouterFunction.class)).build();

    }

    @Test
    void testGetDeliveryInstructionByCity() {
        client.get().uri("/di/byCity/mtl")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().is2xxSuccessful()
            .returnResult(DeliveryInstruction.class)
            .consumeWith(result -> {
                result.getResponseBody()
                    .parallel(4)
                    .subscribe(di -> Assertions.assertEquals(di.getCountry(), "CAA"));
            });


    }

    @Test
    void addDeliveryInstruction() {
    }
}