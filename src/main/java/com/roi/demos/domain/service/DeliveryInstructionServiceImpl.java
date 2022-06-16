package com.roi.demos.domain.service;

import com.roi.demos.domain.entity.DeliveryInstruction;
import com.roi.demos.domain.repository.StubDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryInstructionServiceImpl implements DeliveryInstructionService{

    private final StubDataService stubDataService;

    @Override
    public Mono<DeliveryInstruction> getDeliveryInstructionById(String id) {
        var deliveryInstruction = stubDataService.getAllDeliveryInstructions().stream()
            .filter(di -> StringUtils.equals(di.getId().toString(), id))
            .findFirst();
        if (deliveryInstruction.isPresent()){
            return Mono.just(deliveryInstruction.get());
        }else{
            return Mono.empty();
        }
    }

    @Override
    public Flux<DeliveryInstruction> getDeliveryInstructionByCity(String city) {
        return Flux.fromIterable(stubDataService.getAllDeliveryInstructions().stream()
            .filter(di -> StringUtils.equalsIgnoreCase(di.getCity(), city))
            .collect(Collectors.toList()));
    }

    @Override
    public Flux<DeliveryInstruction> findAllDeliveryInstructions() {
        return Flux.fromIterable(stubDataService.getAllDeliveryInstructions());
    }
}
