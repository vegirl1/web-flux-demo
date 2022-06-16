package com.roi.demos.domain.service;

import com.roi.demos.domain.entity.DeliveryInstruction;
import com.roi.demos.domain.entity.Trade;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DeliveryInstructionService {
    Mono<DeliveryInstruction> getDeliveryInstructionById(String id);
    Flux<DeliveryInstruction> getDeliveryInstructionByCity(String city);
    Flux<DeliveryInstruction> findAllDeliveryInstructions();
}
