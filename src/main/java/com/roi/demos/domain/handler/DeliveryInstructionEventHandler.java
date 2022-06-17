package com.roi.demos.domain.handler;

import com.roi.demos.domain.entity.DeliveryInstruction;
import com.roi.demos.domain.service.DeliveryInstructionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryInstructionEventHandler {

    private final DeliveryInstructionService diService;

    public Mono<ServerResponse> getTestEvent(ServerRequest request) {
        return ok().body(Flux.just("DI request received"), String.class);
    }

    public Mono<ServerResponse> getDeliveryInstructionByCity(ServerRequest request) {
        var city = request.pathVariable("city");
        var foundDi = diService.getDeliveryInstructionByCity(city);
        return ok()
            .contentType(MediaType.APPLICATION_JSON)
            .body(foundDi, DeliveryInstruction.class);
    }

    public Mono<ServerResponse> addDeliveryInstruction(ServerRequest request) {
        Mono<DeliveryInstruction> requestMono = request.bodyToMono(DeliveryInstruction.class);
        Mono<DeliveryInstruction> mapped = requestMono
            .map(di -> {
                System.out.println("Hello new DI " + di.getName());
                diService.saveDeliveryInstructionTest(di);
                return di;
            })
            .doOnSuccess(di -> System.out.println("OnSuccess -> " + di.toString()));
        return ok().body(mapped, DeliveryInstruction.class);
    }
}
