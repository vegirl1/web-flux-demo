package com.roi.demos.domain.service;

import com.roi.demos.domain.entity.DeliveryInstruction;
import com.roi.demos.domain.repository.DeliveryInstructionRepository;
import com.roi.demos.domain.repository.StubDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeliveryInstructionServiceImpl implements DeliveryInstructionService {

    private final List<DeliveryInstruction> deliveryInstructions;
    private final DeliveryInstructionRepository deliveryInstructionRepository;
    private final StubDataService stubDataService;

    public DeliveryInstructionServiceImpl(DeliveryInstructionRepository deliveryInstructionRepository, StubDataService stubDataService) {
        this.deliveryInstructionRepository = deliveryInstructionRepository;
        this.stubDataService = stubDataService;
        this.deliveryInstructions = stubDataService.getAllDeliveryInstructions();
    }


    @Override
    public Mono<DeliveryInstruction> getDeliveryInstructionById(String id) {
        var deliveryInstruction = deliveryInstructions.stream()
            .filter(di -> StringUtils.equals(di.getId().toString(), id))
            .findFirst();
        if (deliveryInstruction.isPresent()) {
            return Mono.just(deliveryInstruction.get());
        } else {
            return Mono.empty();
        }
    }

    @Override
    public Flux<DeliveryInstruction> getDeliveryInstructionByCity(String city) {
        return Flux.fromIterable(deliveryInstructions.stream()
            .filter(di -> StringUtils.equalsIgnoreCase(di.getCity(), city))
            .collect(Collectors.toList()));
    }

    @Override
    public Flux<DeliveryInstruction> findAllDeliveryInstructions() {
        return deliveryInstructionRepository.findAll();
    }

    @Override
    public void saveDeliveryInstructionTest(DeliveryInstruction deliveryInstruction) {
        log.warn("Save deliveryInstruction -> add to list");
        deliveryInstructions.add(deliveryInstruction);
    }
}
