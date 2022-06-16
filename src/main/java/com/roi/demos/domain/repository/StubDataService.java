package com.roi.demos.domain.repository;

import com.roi.demos.domain.entity.DeliveryInstruction;
import com.roi.demos.domain.entity.Trade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class StubDataService {

    private static final List<Trade> STUB_TRADES = IntStream.range(1, 20)
        .boxed()
        .map(index -> {
            if (index % 2 == 0) {
                return tradeBuilder("Cash", "CAD_trade_" + index).build();
            } else {
                return tradeBuilder("Security", "RBC_share_" + index).build();
            }
        }).collect(Collectors.toList());

    private static final List<DeliveryInstruction> STUB_DELIVERY_INSTRUCTIONS = IntStream.range(1, 20)
        .boxed()
        .map(index -> deliveryInstructionBuilder("name_" + index, "address_" + index)
            .build()).collect(Collectors.toList());

    public List<Trade> getAllTrades() {
        return STUB_TRADES;
    }

    public List<DeliveryInstruction> getAllDeliveryInstructions() {
        return STUB_DELIVERY_INSTRUCTIONS;
    }

    private static Trade.TradeBuilder tradeBuilder(String type, String description) {
        return Trade.builder()
            .id(UUID.randomUUID())
            .description(description)
            .price(BigDecimal.TEN)
            .quantity(BigDecimal.TEN)
            .settlementDate(LocalDateTime.now())
            .type(type)
            .deliveryInstructions(List.of(
                deliveryInstructionBuilder("name1", "address1").build()));
    }

    private static DeliveryInstruction.DeliveryInstructionBuilder deliveryInstructionBuilder(String name,
                                                                                             String address) {
        return DeliveryInstruction.builder()
            .id(UUID.randomUUID())
            .name(name)
            .address(address)
            .city("Mtl")
            .country("CA");
    }

}
