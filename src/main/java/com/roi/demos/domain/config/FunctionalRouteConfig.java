package com.roi.demos.domain.config;

import com.roi.demos.domain.handler.DeliveryInstructionEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class FunctionalRouteConfig {

    @Bean
    public RouterFunction<ServerResponse> buildFunctionalRoutes(DeliveryInstructionEventHandler diHandler) {
        return RouterFunctions.route()
            .path("di",
                event -> event.nest(accept(MediaType.APPLICATION_JSON),
                    path -> path.GET("test", diHandler::getTestEvent)
                        .GET("byCity/{city:[a-z]+}", diHandler::getDeliveryInstructionByCity)
                        .POST("addDi", diHandler::addDeliveryInstruction)))
            .build();

    }
}
